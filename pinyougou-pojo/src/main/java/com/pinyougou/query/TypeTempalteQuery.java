package com.pinyougou.query;

import java.io.Serializable;
/**
 * 商品模板查询条件
 * @author 89568
 *
 */
public class TypeTempalteQuery implements Serializable{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
