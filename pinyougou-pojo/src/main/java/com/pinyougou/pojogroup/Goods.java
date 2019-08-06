package com.pinyougou.pojogroup;


import java.io.Serializable;
import java.util.List;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojo.TbGoodsDesc;
import com.pinyougou.pojo.TbItem;

/**
 * 商品组合类
 * @author 89568
 *
 */

public class Goods implements Serializable{
	/**
	 * 商品信息对象
	 */
	private TbGoods goods;
	/**
	 * 商品描述的对象
	 */
	private TbGoodsDesc goodsDesc;
	/**
	 * 商品的sku对象
	 */
	private List<TbItem> itemList;
	public TbGoods getGoods() {
		return goods;
	}
	public void setGoods(TbGoods goods) {
		this.goods = goods;
	}
	public TbGoodsDesc getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(TbGoodsDesc goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public List<TbItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<TbItem> itemList) {
		this.itemList = itemList;
	}
	
	
	
}
