package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.query.BrandQuery;

import entity.PageResult;

/**
 * 商品业务接口层
 * @author Administrator
 *
 */
public interface BrandService {
	
	/**
	 * 查询所有的品牌列表信息
	 * @return
	 */
	public List<TbBrand> findAll();
	
	/**
	 * @param query 查询封装条件
	 * @param pageNum 当前页
	 * @param pageSize每页显示多少条
	 */
	public PageResult pageList(BrandQuery brandQuery,int pageNum,int pageSize);
	
	/**
	 * 添加品牌信息
	 */
	public void seveOrupdate(TbBrand brand);
	
	/**
	 * 根据id查找brand
	 */
	public TbBrand findById(int id);
	/**
	 * 根据id批量删除
	 * @param id
	 */
	public void batchDelBrand(Long[] id);
	
	/**
	 * 回显josn格式给前端
	 */
	public List<Map> selectOptionList();
}
