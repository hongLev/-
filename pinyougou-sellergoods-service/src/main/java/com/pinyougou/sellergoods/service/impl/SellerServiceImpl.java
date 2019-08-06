package com.pinyougou.sellergoods.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import com.pinyougou.pojo.TbSellerExample.Criteria;
import com.pinyougou.query.SellerQuery;
import com.pinyougou.sellergoods.service.SellerService;

import entity.PageResult;
@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private TbSellerMapper sellerMapping;
	
	@Override
	public void insert(TbSeller seller) {
		// TODO Auto-generated method stub
		//封装商家状态
		seller.setStatus("0");//商家申请待审核
		seller.setCreateTime(new Date());//商家申请时间
		this.sellerMapping.insert(seller);
	}
	//分页查询
	@Override
	public PageResult pageList(SellerQuery query, int num, int size) {
		// TODO Auto-generated method stub
		PageHelper.startPage(num, size);
		TbSellerExample example = new TbSellerExample();
		Criteria criteria = example.createCriteria();
		if(query != null){
			if(query.getName() != null && query.getName() != ""){
				criteria.andNameLike("%"+query.getName()+"%");
			}
			if(query.getNickName() !=null && query.getNickName() != ""){
				criteria.andNickNameLike("%"+query.getNickName()+"%");
			}
			if(query.getStart() !=null && query.getStart() !=""){
				criteria.andStatusEqualTo(query.getStart());
			}
		}
		Page<TbSeller> page = (Page<TbSeller>) this.sellerMapping.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}
	//根据主键找到id
	@Override
	public TbSeller findSellerById(String id) {
		// TODO Auto-generated method stub
		 
		return this.sellerMapping.selectByPrimaryKey(id);
	}
	//审核商家  修改状态
	@Override
	public void updataSellerStart(String id, String start) {
		// TODO Auto-generated method stub
		TbSeller seller = this.sellerMapping.selectByPrimaryKey(id);
		seller.setStatus(start);
		this.sellerMapping.updateByPrimaryKey(seller);
	}

}
