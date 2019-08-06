package com.pinyougou.sellergoods.service;


import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.query.TypeTempalteQuery;

import entity.PageResult;
/**
 * 数据模板业务接口类
 * @author 89568
 *
 */
public interface TypeTempalteService {
	/**
	 * 查询条件显示
	 * @param query
	 * @param num
	 * @param size
	 * @return
	 */
	public PageResult pageList(TypeTempalteQuery query,int num,int size);
	/**
	 * 新增模板
	 * @param typeTemplate
	 */
	public void seavOfupdata(TbTypeTemplate typeTemplate);
	/**
	 * 根据主键id查找
	 * @param id
	 * @return
	 */
	public TbTypeTemplate findOne(Long id);
	/**
	 * 批量删除id
	 * @param id
	 */
	public void deleteById(Long[] id);
	
	/**
	 * 返回规格列表
	 * @return
	 */
	public List<Map> findSpecList(Long id);

}
