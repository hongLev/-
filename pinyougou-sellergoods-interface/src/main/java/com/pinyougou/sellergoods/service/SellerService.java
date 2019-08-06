package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.query.SellerQuery;

import entity.PageResult;

/**
 * 商家业务接口层
 * @author 89568
 *
 */
public interface SellerService {
	/**
	 * 新增用户
	 * @param seller
	 */
	public void insert(TbSeller seller);
	/**
	 * 分页查询用户
	 */
	public PageResult pageList(SellerQuery query,int num,int size);
	
	/**
	 * 根据主键id查找用户
	 */
	public TbSeller findSellerById(String id);
	/**
	 * 审核商家
	 * @param id
	 * @param start
	 */
	public void updataSellerStart(String id,String start);
	
}
