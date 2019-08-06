package com.pinyougou.query;

import java.io.Serializable;

/**
 * 商家查询页分类
 * @author 89568
 *
 */
public class SellerQuery implements Serializable{
	/**
	 * 店铺名称	
	 */
	private String nickName;
	/**
	 *	 公司名称
	 */
	private String name;
	/**
	 * 商家状态
	 */
	private String start;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	
}
