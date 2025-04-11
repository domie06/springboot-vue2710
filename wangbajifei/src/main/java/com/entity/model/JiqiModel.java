package com.entity.model;

import com.entity.JiqiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 机器
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiqiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 机器编号
     */
    private String jiqiUuidNumber;


    /**
     * 地区
     */
    private Integer jiqiQuTypes;


    /**
     * 机器位置
     */
    private String jiqiAddress;


    /**
     * 机器详情
     */
    private String jiqiContent;


    /**
     * 使用
     */
    private Integer jiqiShiyongTypes;


    /**
     * 逻辑删除
     */
    private Integer jiqiDelete;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 获取：机器编号
	 */
    public String getJiqiUuidNumber() {
        return jiqiUuidNumber;
    }


    /**
	 * 设置：机器编号
	 */
    public void setJiqiUuidNumber(String jiqiUuidNumber) {
        this.jiqiUuidNumber = jiqiUuidNumber;
    }
    /**
	 * 获取：地区
	 */
    public Integer getJiqiQuTypes() {
        return jiqiQuTypes;
    }


    /**
	 * 设置：地区
	 */
    public void setJiqiQuTypes(Integer jiqiQuTypes) {
        this.jiqiQuTypes = jiqiQuTypes;
    }
    /**
	 * 获取：机器位置
	 */
    public String getJiqiAddress() {
        return jiqiAddress;
    }


    /**
	 * 设置：机器位置
	 */
    public void setJiqiAddress(String jiqiAddress) {
        this.jiqiAddress = jiqiAddress;
    }
    /**
	 * 获取：机器详情
	 */
    public String getJiqiContent() {
        return jiqiContent;
    }


    /**
	 * 设置：机器详情
	 */
    public void setJiqiContent(String jiqiContent) {
        this.jiqiContent = jiqiContent;
    }
    /**
	 * 获取：使用
	 */
    public Integer getJiqiShiyongTypes() {
        return jiqiShiyongTypes;
    }


    /**
	 * 设置：使用
	 */
    public void setJiqiShiyongTypes(Integer jiqiShiyongTypes) {
        this.jiqiShiyongTypes = jiqiShiyongTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJiqiDelete() {
        return jiqiDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setJiqiDelete(Integer jiqiDelete) {
        this.jiqiDelete = jiqiDelete;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
