package com.pinyougou.content.service;

import java.util.List;

import com.pinyougou.pojo.TbContent;

public interface ContentSercvice {
	/**
	 * 根据id查找
	 * @param categoryId
	 * @return
	 */
	public List<TbContent> findByCategoryId(Long categoryId);
	/**
	 * 增加
	 * @param content
	 */
	public void add(TbContent content);
	/**
	 * 修改
	 * @param content
	 */
	public void updata(TbContent content);
	/**
	 * 删除
	 * @param content
	 */
	public void delete(TbContent content);
}
