package com.entity.view;

import com.entity.JiqiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 机器
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jiqi")
public class JiqiView extends JiqiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 地区的值
		*/
		private String jiqiQuValue;
		/**
		* 使用的值
		*/
		private String jiqiShiyongValue;
		/**
		* 使用的值
		*/
		private String beizhu1111;



	public JiqiView() {

	}

	public JiqiView(JiqiEntity jiqiEntity) {
		try {
			BeanUtils.copyProperties(this, jiqiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	public String getBeizhu1111() {
		return beizhu1111;
	}

	public void setBeizhu1111(String beizhu1111) {
		this.beizhu1111 = beizhu1111;
	}
}
