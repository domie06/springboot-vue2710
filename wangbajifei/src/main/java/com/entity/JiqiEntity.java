package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 机器
 *
 * @author 
 * @email
 */
@TableName("jiqi")
public class JiqiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiqiEntity() {

	}

	public JiqiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 机器编号
     */
    @TableField(value = "jiqi_uuid_number")

    private String jiqiUuidNumber;


    /**
     * 地区
     */
    @TableField(value = "jiqi_qu_types")

    private Integer jiqiQuTypes;


    /**
     * 机器位置
     */
    @TableField(value = "jiqi_address")

    private String jiqiAddress;


    /**
     * 机器详情
     */
    @TableField(value = "jiqi_content")

    private String jiqiContent;


    /**
     * 使用
     */
    @TableField(value = "jiqi_shiyong_types")

    private Integer jiqiShiyongTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "jiqi_delete")

    private Integer jiqiDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：机器编号
	 */
    public String getJiqiUuidNumber() {
        return jiqiUuidNumber;
    }


    /**
	 * 获取：机器编号
	 */

    public void setJiqiUuidNumber(String jiqiUuidNumber) {
        this.jiqiUuidNumber = jiqiUuidNumber;
    }
    /**
	 * 设置：地区
	 */
    public Integer getJiqiQuTypes() {
        return jiqiQuTypes;
    }


    /**
	 * 获取：地区
	 */

    public void setJiqiQuTypes(Integer jiqiQuTypes) {
        this.jiqiQuTypes = jiqiQuTypes;
    }
    /**
	 * 设置：机器位置
	 */
    public String getJiqiAddress() {
        return jiqiAddress;
    }


    /**
	 * 获取：机器位置
	 */

    public void setJiqiAddress(String jiqiAddress) {
        this.jiqiAddress = jiqiAddress;
    }
    /**
	 * 设置：机器详情
	 */
    public String getJiqiContent() {
        return jiqiContent;
    }


    /**
	 * 获取：机器详情
	 */

    public void setJiqiContent(String jiqiContent) {
        this.jiqiContent = jiqiContent;
    }
    /**
	 * 设置：使用
	 */
    public Integer getJiqiShiyongTypes() {
        return jiqiShiyongTypes;
    }


    /**
	 * 获取：使用
	 */

    public void setJiqiShiyongTypes(Integer jiqiShiyongTypes) {
        this.jiqiShiyongTypes = jiqiShiyongTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJiqiDelete() {
        return jiqiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJiqiDelete(Integer jiqiDelete) {
        this.jiqiDelete = jiqiDelete;
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

    @Override
    public String toString() {
        return "Jiqi{" +
            "id=" + id +
            ", jiqiUuidNumber=" + jiqiUuidNumber +
            ", jiqiQuTypes=" + jiqiQuTypes +
            ", jiqiAddress=" + jiqiAddress +
            ", jiqiContent=" + jiqiContent +
            ", jiqiShiyongTypes=" + jiqiShiyongTypes +
            ", jiqiDelete=" + jiqiDelete +
            ", createTime=" + createTime +
        "}";
    }
}
