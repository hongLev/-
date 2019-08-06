package com.pinyougou.query;

import java.io.Serializable;
/**
 * 品牌列表查询的对象
 * @author 89568
 *
 */

public class BrandQuery implements Serializable{
	//品牌名称
    private String name;
    //品牌首字母
    private String firstChar;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
    
	
}
