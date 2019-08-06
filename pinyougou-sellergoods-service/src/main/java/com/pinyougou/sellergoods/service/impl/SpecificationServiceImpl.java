package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.query.SpecificationQuery;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	/**
	 * 分页查询
	 */
	public PageResult PageResult(SpecificationQuery query, int pageNum, int pageSize) {

		// 分页查询
		PageHelper.startPage(pageNum, pageSize);
		// 创建查询对象
		TbSpecificationExample example = new TbSpecificationExample();
		// 创建查询条件
		Criteria criteria = example.createCriteria();
		if (query != null) {
			if (query.getName() != null && query.getName() != "") {
				criteria.andSpecNameLike("%" + query.getName() + "%");
			}
		}
		Page<TbSpecification> page = (Page<TbSpecification>) this.specificationMapper.selectByExample(example);
		// 返回pageResult对象
		return new entity.PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void seavOrupdata(Specification specification) {
		// TODO Auto-generated method stub
		if (specification != null && specification.getSpecification().getId() != null) {
			// 修改//先保存规格
			this.specificationMapper.updateByPrimaryKey(specification.getSpecification());

			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			// 创建查询条件
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(specification.getSpecification().getId());
			// 批量删除规格选项
			this.specificationOptionMapper.deleteByExample(example);
			// 依此新增
			for (TbSpecificationOption specificationOption : specification.getSpecificationOption()) {
				specificationOption.setSpecId(specification.getSpecification().getId());// 设置id
				this.specificationOptionMapper.insert(specificationOption);// 新增选项规则
			}
		}else{
			// 新增
			this.specificationMapper.insert(specification.getSpecification());
			for (TbSpecificationOption specificationOption : specification.getSpecificationOption()) {
				specificationOption.setSpecId(specification.getSpecification().getId());// 设置id
				this.specificationOptionMapper.insert(specificationOption);// 新增选项规则
			}
		}
	}

	@Override
	public Specification findOne(Long id) {
		// TODO Auto-generated method stub
		Specification specification = new Specification();
		// 获取规格
		specification.setSpecification(this.specificationMapper.selectByPrimaryKey(id));
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		// 创建查询条件
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(specification.getSpecification().getId());
		specification.setSpecificationOption(this.specificationOptionMapper.selectByExample(example));
		return specification;
	}

	@Override
	public void delById(Long[] ids) {
		// TODO Auto-generated method stub

		for (Long id : ids) {
			// 先删除规格选项
			Specification specification = new Specification();
			// 获取规格
			specification.setSpecification(this.specificationMapper.selectByPrimaryKey(id));

			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			// 创建查询条件
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(specification.getSpecification().getId());
			// 批量删除规格选项
			this.specificationOptionMapper.deleteByExample(example);
			// 删除规格
			this.specificationMapper.deleteByPrimaryKey(id);
		}

	}

	@Override
	public List<Map> selectSpecificationList() {
		// TODO Auto-generated method stub
		return this.specificationMapper.selectSpecificationList();
	}

}
