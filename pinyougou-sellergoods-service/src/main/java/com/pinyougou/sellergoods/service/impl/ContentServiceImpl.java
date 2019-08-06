package com.pinyougou.sellergoods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.sellergoods.service.ContentService;

import entity.PageResult;

/**
 * 实现层
 * @author 89568
 *
 */
@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper contentMapper;
	/**
	 * 分页
	 */
	@Override
	public PageResult PageList(int page, int size) {
		PageHelper.startPage(page, size);
		Page<TbContent> page1 = (Page<TbContent>)this.contentMapper.selectByExample(null);
		return new PageResult(page1.getTotal(), page1.getResult());
	}

	@Override
	public void insert(TbContent content) {
		// TODO Auto-generated method stub
		
	}

	
}
