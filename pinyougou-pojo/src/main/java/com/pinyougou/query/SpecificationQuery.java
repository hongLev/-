package com.pinyougou.query;

import java.io.Serializable;

/**
 * 规格管理
 * @author 89568
 *
 */
public class SpecificationQuery implements Serializable{
	//规格名称
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
