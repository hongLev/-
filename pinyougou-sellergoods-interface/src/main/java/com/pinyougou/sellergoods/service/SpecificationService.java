package com.pinyougou.sellergoods.service;


import java.util.List;
import java.util.Map;

import com.pinyougou.pojogroup.Specification;
import com.pinyougou.query.SpecificationQuery;

import entity.PageResult;
/**
 * 规格管理业务层
 * @author 89568
 *
 */
public interface SpecificationService {
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public PageResult PageResult(SpecificationQuery query,int pageNum,int pageSize);
	/**
	 * 修改新增 共通类
	 * @param specification
	 */
	public void seavOrupdata(Specification specification);
	/**
	 * 根据id 查找
	 * @param id
	 * @return
	 */
	public Specification findOne(Long id);
	/**
	 * 根据ID批量删除
	 * @param id
	 */
	public void delById(Long[] ids);
	
	public List<Map> selectSpecificationList();
}
