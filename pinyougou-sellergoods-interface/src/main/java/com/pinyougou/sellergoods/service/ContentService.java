package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbContent;

import entity.PageResult;

/**
 * 广告管理控制层
 * @author 89568
 *
 */
public interface ContentService {
	/**
	 * 分页显示
	 * @param page
	 * @param size
	 * @return
	 */
	public PageResult PageList(int page,int size);
	/**
	 * 增加
	 * @param content
	 */
	public void insert(TbContent content);
}
