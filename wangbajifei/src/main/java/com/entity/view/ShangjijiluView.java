package com.entity.view;

import com.entity.ShangjijiluEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 上机记录
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shangjijilu")
public class ShangjijiluView extends ShangjijiluEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 状态的值
		*/
		private String shangjijiluZhuangtaiValue;



		//级联表 jiqi
			/**
			* 机器编号
			*/
			private String jiqiUuidNumber;
			/**
			* 地区
			*/
			private Integer jiqiQuTypes;
				/**
				* 地区的值
				*/
				private String jiqiQuValue;
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
				* 使用的值
				*/
				private String jiqiShiyongValue;
			/**
			* 逻辑删除
			*/
			private Integer jiqiDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;

	public ShangjijiluView() {

	}

	public ShangjijiluView(ShangjijiluEntity shangjijiluEntity) {
		try {
			BeanUtils.copyProperties(this, shangjijiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 状态的值
			*/
			public String getShangjijiluZhuangtaiValue() {
				return shangjijiluZhuangtaiValue;
			}
			/**
			* 设置： 状态的值
			*/
			public void setShangjijiluZhuangtaiValue(String shangjijiluZhuangtaiValue) {
				this.shangjijiluZhuangtaiValue = shangjijiluZhuangtaiValue;
			}







				//级联表的get和set jiqi
					/**
					* 获取： 机器编号
					*/
					public String getJiqiUuidNumber() {
						return jiqiUuidNumber;
					}
					/**
					* 设置： 机器编号
					*/
					public void setJiqiUuidNumber(String jiqiUuidNumber) {
						this.jiqiUuidNumber = jiqiUuidNumber;
					}
					/**
					* 获取： 地区
					*/
					public Integer getJiqiQuTypes() {
						return jiqiQuTypes;
					}
					/**
					* 设置： 地区
					*/
					public void setJiqiQuTypes(Integer jiqiQuTypes) {
						this.jiqiQuTypes = jiqiQuTypes;
					}


						/**
						* 获取： 地区的值
						*/
						public String getJiqiQuValue() {
							return jiqiQuValue;
						}
						/**
						* 设置： 地区的值
						*/
						public void setJiqiQuValue(String jiqiQuValue) {
							this.jiqiQuValue = jiqiQuValue;
						}
					/**
					* 获取： 机器位置
					*/
					public String getJiqiAddress() {
						return jiqiAddress;
					}
					/**
					* 设置： 机器位置
					*/
					public void setJiqiAddress(String jiqiAddress) {
						this.jiqiAddress = jiqiAddress;
					}
					/**
					* 获取： 机器详情
					*/
					public String getJiqiContent() {
						return jiqiContent;
					}
					/**
					* 设置： 机器详情
					*/
					public void setJiqiContent(String jiqiContent) {
						this.jiqiContent = jiqiContent;
					}
					/**
					* 获取： 使用
					*/
					public Integer getJiqiShiyongTypes() {
						return jiqiShiyongTypes;
					}
					/**
					* 设置： 使用
					*/
					public void setJiqiShiyongTypes(Integer jiqiShiyongTypes) {
						this.jiqiShiyongTypes = jiqiShiyongTypes;
					}


						/**
						* 获取： 使用的值
						*/
						public String getJiqiShiyongValue() {
							return jiqiShiyongValue;
						}
						/**
						* 设置： 使用的值
						*/
						public void setJiqiShiyongValue(String jiqiShiyongValue) {
							this.jiqiShiyongValue = jiqiShiyongValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getJiqiDelete() {
						return jiqiDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setJiqiDelete(Integer jiqiDelete) {
						this.jiqiDelete = jiqiDelete;
					}







				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}



}
