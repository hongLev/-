package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.query.GoodsQuery;

import entity.PageResult;

/**
 * 商品业务接口成
 * @author 89568
 *
 */
public interface GoodsService {

	/**
	 * 添加
	 * @param goods
	 */
	public void add(Goods goods);
	/**
	 * 搜索分页排序  前后端共通
	 * @param goods
	 * @param page
	 * @param size
	 * @return
	 */
	public PageResult findPage(GoodsQuery query, int page, int size);
	/**
	 * 上架或者下架
	 */
	public void shelves(Long[] id,String marketable);
	/**
	 * 审核  
	 * @param id
	 */
	public void auditStatus(Long[] id,String status);
	/**
	 * 删除
	 * @param id
	 */
	public void deleteGoods(Long[] id);
	
}
