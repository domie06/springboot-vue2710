
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
 * 机器
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiqi")
public class JiqiController {
    private static final Logger logger = LoggerFactory.getLogger(JiqiController.class);

    @Autowired
    private JiqiService jiqiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ShangjijiluService shangjijiluService;


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
        params.put("jiqiDeleteStart",1);params.put("jiqiDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jiqiService.queryPage(params);

        //字典表数据转换
        List<JiqiView> list =(List<JiqiView>)page.getList();
        for(JiqiView c:list){
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
        JiqiEntity jiqi = jiqiService.selectById(id);
        if(jiqi !=null){
            //entity转view
            JiqiView view = new JiqiView();
            BeanUtils.copyProperties( jiqi , view );//把实体数据重构到view中

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
    public R save(@RequestBody JiqiEntity jiqi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiqi:{}",this.getClass().getName(),jiqi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiqiEntity> queryWrapper = new EntityWrapper<JiqiEntity>()
            .eq("jiqi_uuid_number", jiqi.getJiqiUuidNumber())
            .eq("jiqi_qu_types", jiqi.getJiqiQuTypes())
            .eq("jiqi_address", jiqi.getJiqiAddress())
            .eq("jiqi_shiyong_types", jiqi.getJiqiShiyongTypes())
            .eq("jiqi_delete", jiqi.getJiqiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiqiEntity jiqiEntity = jiqiService.selectOne(queryWrapper);
        if(jiqiEntity==null){
            jiqi.setJiqiDelete(1);
            jiqi.setCreateTime(new Date());
            jiqiService.insert(jiqi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiqiEntity jiqi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiqi:{}",this.getClass().getName(),jiqi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<JiqiEntity> queryWrapper = new EntityWrapper<JiqiEntity>()
            .notIn("id",jiqi.getId())
            .andNew()
            .eq("jiqi_uuid_number", jiqi.getJiqiUuidNumber())
            .eq("jiqi_qu_types", jiqi.getJiqiQuTypes())
            .eq("jiqi_address", jiqi.getJiqiAddress())
            .eq("jiqi_shiyong_types", jiqi.getJiqiShiyongTypes())
            .eq("jiqi_delete", jiqi.getJiqiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiqiEntity jiqiEntity = jiqiService.selectOne(queryWrapper);
        if(jiqiEntity==null){
            jiqiService.updateById(jiqi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<JiqiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JiqiEntity jiqiEntity = new JiqiEntity();
            jiqiEntity.setId(id);
            jiqiEntity.setJiqiDelete(2);
            list.add(jiqiEntity);
        }
        if(list != null && list.size() >0){
            jiqiService.updateBatchById(list);
        }
        return R.ok();
    }
    /**
    * 上机
    */
    @RequestMapping("/shangji")
    public R shangji(Integer id, HttpServletRequest request){
        logger.debug("shangji:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");

        JiqiEntity jiqiEntity = jiqiService.selectById(id);
        if(jiqiEntity == null){
            return R.error("查不到机器");
        }else if(jiqiEntity.getJiqiShiyongTypes() ==2){
            return R.error("机器正在被使用");
        }

        Integer userId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
        if(yonghuEntity== null)
            return R.error("查不到用户");
        else if(yonghuEntity.getNewMoney()<0.0)
            return R.error("当前账户余额小于0元,无法上机");


        ShangjijiluEntity shangjijiluEntity1 = shangjijiluService.selectOne(new EntityWrapper<ShangjijiluEntity>().eq("yonghu_id", userId).eq("shangjijilu_zhuangtai_types", 1));
        if(shangjijiluEntity1 != null){
            return R.error("当前用户正在上机,请把上机机器下机后再进行上机操作");
        }



        Integer jiqiQuTypes = jiqiEntity.getJiqiQuTypes();


        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(new EntityWrapper<DictionaryEntity>().eq("dic_code", "jiqi_qu_types").eq("code_index", jiqiQuTypes));
        if(dictionaryEntity == null)
            return R.error("当前机器没有关联价格,无法上机,请联系管理员");

        ShangjijiluEntity shangjijiluEntity = new ShangjijiluEntity();
        shangjijiluEntity.setCreateTime(new Date());
        shangjijiluEntity.setInsertTime(new Date());
        shangjijiluEntity.setYonghuId(userId);
        shangjijiluEntity.setJiqiId(id);
        shangjijiluEntity.setShangjijiluUuidNumber(String.valueOf(new Date().getTime()));
        shangjijiluEntity.setShangjijiluZhuangtaiTypes(1);
        shangjijiluEntity.setShangjiTime(new Date());


        jiqiEntity.setJiqiShiyongTypes(2);
        jiqiService.updateById(jiqiEntity);
        shangjijiluService.insert(shangjijiluEntity);





        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiqiEntity> jiqiList = new ArrayList<>();//上传的东西
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
                            JiqiEntity jiqiEntity = new JiqiEntity();
//                            jiqiEntity.setJiqiUuidNumber(data.get(0));                    //机器编号 要改的
//                            jiqiEntity.setJiqiQuTypes(Integer.valueOf(data.get(0)));   //地区 要改的
//                            jiqiEntity.setJiqiAddress(data.get(0));                    //机器位置 要改的
//                            jiqiEntity.setJiqiContent("");//照片
//                            jiqiEntity.setJiqiShiyongTypes(Integer.valueOf(data.get(0)));   //使用 要改的
//                            jiqiEntity.setJiqiDelete(1);//逻辑删除字段
//                            jiqiEntity.setCreateTime(date);//时间
                            jiqiList.add(jiqiEntity);


                            //把要查询是否重复的字段放入map中
                                //机器编号
                                if(seachFields.containsKey("jiqiUuidNumber")){
                                    List<String> jiqiUuidNumber = seachFields.get("jiqiUuidNumber");
                                    jiqiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiqiUuidNumber = new ArrayList<>();
                                    jiqiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiqiUuidNumber",jiqiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //机器编号
                        List<JiqiEntity> jiqiEntities_jiqiUuidNumber = jiqiService.selectList(new EntityWrapper<JiqiEntity>().in("jiqi_uuid_number", seachFields.get("jiqiUuidNumber")).eq("jiqi_delete", 1));
                        if(jiqiEntities_jiqiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiqiEntity s:jiqiEntities_jiqiUuidNumber){
                                repeatFields.add(s.getJiqiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [机器编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiqiService.insertBatch(jiqiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
