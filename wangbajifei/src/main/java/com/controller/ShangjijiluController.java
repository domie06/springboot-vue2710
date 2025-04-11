
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 上机记录
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shangjijilu")
public class ShangjijiluController {
    private static final Logger logger = LoggerFactory.getLogger(ShangjijiluController.class);

    @Autowired
    private ShangjijiluService shangjijiluService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private JiqiService jiqiService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = shangjijiluService.queryPage(params);

        //字典表数据转换
        List<ShangjijiluView> list =(List<ShangjijiluView>)page.getList();
        for(ShangjijiluView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShangjijiluEntity shangjijilu = shangjijiluService.selectById(id);
        if(shangjijilu !=null){
            //entity转view
            ShangjijiluView view = new ShangjijiluView();
            BeanUtils.copyProperties( shangjijilu , view );//把实体数据重构到view中

                //级联表
                JiqiEntity jiqi = jiqiService.selectById(shangjijilu.getJiqiId());
                if(jiqi != null){
                    BeanUtils.copyProperties( jiqi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiqiId(jiqi.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(shangjijilu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShangjijiluEntity shangjijilu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shangjijilu:{}",this.getClass().getName(),shangjijilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            shangjijilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ShangjijiluEntity> queryWrapper = new EntityWrapper<ShangjijiluEntity>()
            .eq("jiqi_id", shangjijilu.getJiqiId())
            .eq("yonghu_id", shangjijilu.getYonghuId())
            .eq("shangjijilu_uuid_number", shangjijilu.getShangjijiluUuidNumber())
            .eq("xiaoshi", shangjijilu.getXiaoshi())
            .eq("shangjijilu_zhuangtai_types", shangjijilu.getShangjijiluZhuangtaiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangjijiluEntity shangjijiluEntity = shangjijiluService.selectOne(queryWrapper);
        if(shangjijiluEntity==null){
            shangjijilu.setInsertTime(new Date());
            shangjijilu.setCreateTime(new Date());
            shangjijiluService.insert(shangjijilu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShangjijiluEntity shangjijilu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shangjijilu:{}",this.getClass().getName(),shangjijilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            shangjijilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ShangjijiluEntity> queryWrapper = new EntityWrapper<ShangjijiluEntity>()
            .notIn("id",shangjijilu.getId())
            .andNew()
            .eq("jiqi_id", shangjijilu.getJiqiId())
            .eq("yonghu_id", shangjijilu.getYonghuId())
            .eq("shangjijilu_uuid_number", shangjijilu.getShangjijiluUuidNumber())
            .eq("xiaoshi", shangjijilu.getXiaoshi())
            .eq("shangjijilu_zhuangtai_types", shangjijilu.getShangjijiluZhuangtaiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShangjijiluEntity shangjijiluEntity = shangjijiluService.selectOne(queryWrapper);
        if(shangjijiluEntity==null){
            shangjijiluService.updateById(shangjijilu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 后端修改
    */
    @RequestMapping("/xiaji")
    public R xiaji(Integer id, HttpServletRequest request){
        logger.debug("xiaji方法:,,Controller:{},,id:{}",this.getClass().getName(),id);

        String role = String.valueOf(request.getSession().getAttribute("role"));

        Date date = new Date();
        ShangjijiluEntity shangjijiluEntity = shangjijiluService.selectById(id);
        if(shangjijiluEntity == null)
            return R.error("查不到上机记录");
        JiqiEntity jiqiEntity = jiqiService.selectById(shangjijiluEntity.getJiqiId());
        if(jiqiEntity == null){
            return R.error("查不到上机的机器,请确认机器管理中机器是否存在");
        }

        long diff = date.getTime() - shangjijiluEntity.getShangjiTime().getTime();
        if(diff<(60 * 60 * 1000)){
            shangjijiluEntity.setXiaoshi(1);
        }else{
            shangjijiluEntity.setXiaoshi(Long.valueOf(diff / (60 * 60 * 1000)).intValue()+1);;
        }





        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(new EntityWrapper<DictionaryEntity>().eq("dic_code", "jiqi_qu_types").eq("code_index", jiqiEntity.getJiqiQuTypes()));
        if(dictionaryEntity == null)
            return R.error("当前机器没有关联价格,无法上机,请联系管理员");

        double huafei = new BigDecimal(dictionaryEntity.getBeizhu()).multiply(new BigDecimal(shangjijiluEntity.getXiaoshi())).doubleValue();

        shangjijiluEntity.setHuafeijine(huafei);
        shangjijiluEntity.setXiajiTime(date);
        shangjijiluEntity.setShangjijiluZhuangtaiTypes(2);


        YonghuEntity yonghuEntity = yonghuService.selectById(shangjijiluEntity.getYonghuId());
        yonghuEntity.setNewMoney(yonghuEntity.getNewMoney()-huafei);
        yonghuService.updateById(yonghuEntity);


        jiqiEntity.setJiqiShiyongTypes(1);
        jiqiService.updateById(jiqiEntity);

        shangjijiluService.updateById(shangjijiluEntity);

        return R.ok();




    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shangjijiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ShangjijiluEntity> shangjijiluList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ShangjijiluEntity shangjijiluEntity = new ShangjijiluEntity();
//                            shangjijiluEntity.setJiqiId(Integer.valueOf(data.get(0)));   //机器 要改的
//                            shangjijiluEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shangjijiluEntity.setShangjijiluUuidNumber(data.get(0));                    //唯一编号 要改的
//                            shangjijiluEntity.setShangjiTime(new Date(data.get(0)));          //上机时间 要改的
//                            shangjijiluEntity.setXiajiTime(new Date(data.get(0)));          //下机时间 要改的
//                            shangjijiluEntity.setXiaoshi(Integer.valueOf(data.get(0)));   //小时 要改的
//                            shangjijiluEntity.setHuafeijine(data.get(0));                    //花费金额 要改的
//                            shangjijiluEntity.setShangjijiluZhuangtaiTypes(Integer.valueOf(data.get(0)));   //状态 要改的
//                            shangjijiluEntity.setInsertTime(date);//时间
//                            shangjijiluEntity.setCreateTime(date);//时间
                            shangjijiluList.add(shangjijiluEntity);


                            //把要查询是否重复的字段放入map中
                                //唯一编号
                                if(seachFields.containsKey("shangjijiluUuidNumber")){
                                    List<String> shangjijiluUuidNumber = seachFields.get("shangjijiluUuidNumber");
                                    shangjijiluUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shangjijiluUuidNumber = new ArrayList<>();
                                    shangjijiluUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shangjijiluUuidNumber",shangjijiluUuidNumber);
                                }
                        }

                        //查询是否重复
                         //唯一编号
                        List<ShangjijiluEntity> shangjijiluEntities_shangjijiluUuidNumber = shangjijiluService.selectList(new EntityWrapper<ShangjijiluEntity>().in("shangjijilu_uuid_number", seachFields.get("shangjijiluUuidNumber")));
                        if(shangjijiluEntities_shangjijiluUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShangjijiluEntity s:shangjijiluEntities_shangjijiluUuidNumber){
                                repeatFields.add(s.getShangjijiluUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shangjijiluService.insertBatch(shangjijiluList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
