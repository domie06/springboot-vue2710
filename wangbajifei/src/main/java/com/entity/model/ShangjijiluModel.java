package com.entity.model;

import com.entity.ShangjijiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 上机记录
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShangjijiluModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 机器
     */
    private Integer jiqiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 唯一编号
     */
    private String shangjijiluUuidNumber;


    /**
     * 上机时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date shangjiTime;


    /**
     * 下机时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date xiajiTime;


    /**
     * 小时
     */
    private Integer xiaoshi;


    /**
     * 花费金额
     */
    private Double huafeijine;


    /**
     * 状态
     */
    private Integer shangjijiluZhuangtaiTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：机器
	 */
    public Integer getJiqiId() {
        return jiqiId;
    }


    /**
	 * 设置：机器
	 */
    public void setJiqiId(Integer jiqiId) {
        this.jiqiId = jiqiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：唯一编号
	 */
    public String getShangjijiluUuidNumber() {
        return shangjijiluUuidNumber;
    }


    /**
	 * 设置：唯一编号
	 */
    public void setShangjijiluUuidNumber(String shangjijiluUuidNumber) {
        this.shangjijiluUuidNumber = shangjijiluUuidNumber;
    }
    /**
	 * 获取：上机时间
	 */
    public Date getShangjiTime() {
        return shangjiTime;
    }


    /**
	 * 设置：上机时间
	 */
    public void setShangjiTime(Date shangjiTime) {
        this.shangjiTime = shangjiTime;
    }
    /**
	 * 获取：下机时间
	 */
    public Date getXiajiTime() {
        return xiajiTime;
    }


    /**
	 * 设置：下机时间
	 */
    public void setXiajiTime(Date xiajiTime) {
        this.xiajiTime = xiajiTime;
    }
    /**
	 * 获取：小时
	 */
    public Integer getXiaoshi() {
        return xiaoshi;
    }


    /**
	 * 设置：小时
	 */
    public void setXiaoshi(Integer xiaoshi) {
        this.xiaoshi = xiaoshi;
    }
    /**
	 * 获取：花费金额
	 */
    public Double getHuafeijine() {
        return huafeijine;
    }


    /**
	 * 设置：花费金额
	 */
    public void setHuafeijine(Double huafeijine) {
        this.huafeijine = huafeijine;
    }
    /**
	 * 获取：状态
	 */
    public Integer getShangjijiluZhuangtaiTypes() {
        return shangjijiluZhuangtaiTypes;
    }


    /**
	 * 设置：状态
	 */
    public void setShangjijiluZhuangtaiTypes(Integer shangjijiluZhuangtaiTypes) {
        this.shangjijiluZhuangtaiTypes = shangjijiluZhuangtaiTypes;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
