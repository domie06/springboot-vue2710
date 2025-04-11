package com.entity.vo;

import com.entity.ShangjijiluEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 上机记录
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shangjijilu")
public class ShangjijiluVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 机器
     */

    @TableField(value = "jiqi_id")
    private Integer jiqiId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 唯一编号
     */

    @TableField(value = "shangjijilu_uuid_number")
    private String shangjijiluUuidNumber;


    /**
     * 上机时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "shangji_time")
    private Date shangjiTime;


    /**
     * 下机时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xiaji_time")
    private Date xiajiTime;


    /**
     * 小时
     */

    @TableField(value = "xiaoshi")
    private Integer xiaoshi;


    /**
     * 花费金额
     */

    @TableField(value = "huafeijine")
    private Double huafeijine;


    /**
     * 状态
     */

    @TableField(value = "shangjijilu_zhuangtai_types")
    private Integer shangjijiluZhuangtaiTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：机器
	 */
    public Integer getJiqiId() {
        return jiqiId;
    }


    /**
	 * 获取：机器
	 */

    public void setJiqiId(Integer jiqiId) {
        this.jiqiId = jiqiId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：唯一编号
	 */
    public String getShangjijiluUuidNumber() {
        return shangjijiluUuidNumber;
    }


    /**
	 * 获取：唯一编号
	 */

    public void setShangjijiluUuidNumber(String shangjijiluUuidNumber) {
        this.shangjijiluUuidNumber = shangjijiluUuidNumber;
    }
    /**
	 * 设置：上机时间
	 */
    public Date getShangjiTime() {
        return shangjiTime;
    }


    /**
	 * 获取：上机时间
	 */

    public void setShangjiTime(Date shangjiTime) {
        this.shangjiTime = shangjiTime;
    }
    /**
	 * 设置：下机时间
	 */
    public Date getXiajiTime() {
        return xiajiTime;
    }


    /**
	 * 获取：下机时间
	 */

    public void setXiajiTime(Date xiajiTime) {
        this.xiajiTime = xiajiTime;
    }
    /**
	 * 设置：小时
	 */
    public Integer getXiaoshi() {
        return xiaoshi;
    }


    /**
	 * 获取：小时
	 */

    public void setXiaoshi(Integer xiaoshi) {
        this.xiaoshi = xiaoshi;
    }
    /**
	 * 设置：花费金额
	 */
    public Double getHuafeijine() {
        return huafeijine;
    }


    /**
	 * 获取：花费金额
	 */

    public void setHuafeijine(Double huafeijine) {
        this.huafeijine = huafeijine;
    }
    /**
	 * 设置：状态
	 */
    public Integer getShangjijiluZhuangtaiTypes() {
        return shangjijiluZhuangtaiTypes;
    }


    /**
	 * 获取：状态
	 */

    public void setShangjijiluZhuangtaiTypes(Integer shangjijiluZhuangtaiTypes) {
        this.shangjijiluZhuangtaiTypes = shangjijiluZhuangtaiTypes;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
