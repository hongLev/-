package com.pinyougou.query;

import java.io.Serializable;

/**
 * 商品分页
 * @author 89568
 *
 */
public class GoodsQuery implements Serializable{
	//商家id
	private String sellerId;
	//状态
	private String status;
	//商品名称
	private String goodName;
	//是否上架 
	private String marketable;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getMarketable() {
		return marketable;
	}
	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}
	
}
