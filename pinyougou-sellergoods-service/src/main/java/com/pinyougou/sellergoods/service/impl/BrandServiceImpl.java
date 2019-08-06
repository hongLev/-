package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;

import com.pinyougou.query.BrandQuery;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

/**
 * 品牌列表 业务实现类
 * @author Administrator
 *
 */
@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private TbBrandMapper brandMapper;
	
	@Override
	public List<TbBrand> findAll() {
		return this.brandMapper.selectByExample(null);
	}

	@Override
	public PageResult pageList(BrandQuery brandQuery, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		//1分页查询
		PageHelper.startPage(pageNum, pageSize);
		//2创建查询条件
		TbBrandExample example = new TbBrandExample();
		//3创建查询对象
		Criteria criteria = example.createCriteria();
		
		if(brandQuery!=null){
			if(brandQuery.getName()!=null && brandQuery.getName()!=""){
				criteria.andNameLike("%"+brandQuery.getName()+"%");
			}
			if(brandQuery.getFirstChar()!=null && brandQuery.getFirstChar()!=""){
				criteria.andFirstCharLike("%"+brandQuery.getFirstChar()+"%");
			}
		}
		//获取品牌信息
		Page<TbBrand> page = (Page<TbBrand>) this.brandMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public void seveOrupdate(TbBrand brand) {
		// TODO Auto-generated method stub
		if(brand!=null && brand .getId()!=null){
			this.brandMapper.updateByPrimaryKey(brand);
		}else{
			this.brandMapper.insert(brand);
		}
	}

	@Override
	public TbBrand findById(int id) {
		// TODO Auto-generated method stub
		return this.brandMapper.selectByPrimaryKey(Long.parseLong(id+""));
	}

	@Override
	public void batchDelBrand(Long[] id) {
		// TODO Auto-generated method stub
		if(id!=null && id.length>0){
			for(long ids:id){
				//根据id一个一个删
				this.brandMapper.deleteByPrimaryKey(ids);
			}
		}
	}

	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return this.brandMapper.selectList();
	}

}
