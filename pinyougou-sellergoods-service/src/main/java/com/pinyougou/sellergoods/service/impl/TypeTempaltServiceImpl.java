package com.pinyougou.sellergoods.service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.pojo.TbTypeTemplateExample;
import com.pinyougou.pojo.TbTypeTemplateExample.Criteria;
import com.pinyougou.query.TypeTempalteQuery;

import com.pinyougou.sellergoods.service.TypeTempalteService;

import entity.PageResult;

@Service
public class TypeTempaltServiceImpl implements TypeTempalteService {

	@Autowired
	private TbTypeTemplateMapper typeTempaltMapper;
	
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper; 

	@Override
	public PageResult pageList(TypeTempalteQuery query, int num, int size) {
		// TODO Auto-generated method stub
		PageHelper.startPage(num, size);

		TbTypeTemplateExample example = new TbTypeTemplateExample();

		Criteria criteria = example.createCriteria();
		if (query != null) {
			if (query.getName() != null && query.getName() != "") {
				criteria.andNameLike("%" + query.getName() + "%");
			}
		}
		Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) this.typeTempaltMapper.selectByExample(example);

		return new PageResult(page.getTotal(), page.getResult());
	}
	//新增
	@Override
	public void seavOfupdata(TbTypeTemplate typeTemplate) {
		if(typeTemplate.getId() != null && typeTemplate != null){
			this.typeTempaltMapper.updateByPrimaryKey(typeTemplate);
		}else{
			this.typeTempaltMapper.insert(typeTemplate);
		}
		
	}
	@Override
	public TbTypeTemplate findOne(Long id) {
		// TODO Auto-generated method stub
		
		return this.typeTempaltMapper.selectByPrimaryKey(id);
	}
	@Override
	public void deleteById(Long[] id) {
		// TODO Auto-generated method stub
		if(id.length>0){
			for(Long ids:id){
				this.typeTempaltMapper.deleteByPrimaryKey(ids);
			}
		}
	}
	@Override
	public List<Map> findSpecList(Long id) {
		// TODO Auto-generated method stub
		//先获取模板
		TbTypeTemplate typetemplate = this.typeTempaltMapper.selectByPrimaryKey(id);
		//字符串转JSON迭代
		List<Map> list = JSON.parseArray(typetemplate.getSpecIds(), Map.class);
		//获取模板中的规格信息
		for(Map map:list){
			TbSpecificationOptionExample example = new  TbSpecificationOptionExample();
			com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			//转换层Long类型
			criteria.andSpecIdEqualTo(new Long((Integer) map.get("id")));
			List<TbSpecificationOption> options = this.specificationOptionMapper.selectByExample(example);
			
			map.put("options",options);
		}
		
		return list;
	}

}
