package com.hanwei.core.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/***
 * @Description 字典类
 * @author zhuht
 * @Date 2024/1/30 13:37
 * @Since version-1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DictModel implements Serializable{
	private static final long serialVersionUID = 1L;

	public DictModel() {
	}

	public DictModel(String value, String text) {
		this.value = value;
		this.text = text;
	}

	/**
	 * 字典value
	 */
	private String value;
	/**
	 * 字典文本
	 */
	private String text;

	/**
	 * 特殊用途： JgEditableTable
	 * @return
	 */
	public String getTitle() {
		return this.text;
	}

}
