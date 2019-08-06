package com.pinyougou.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.content.service.ContentSercvice;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import com.pinyougou.pojo.TbContentExample.Criteria;
@Service
public class ContentServiceImpl implements ContentSercvice{

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired 
	private RedisTemplate redisTemplate;
	

	@Override
	public List<TbContent> findByCategoryId(Long categoryId) {
		try {
			//查找是否存在
			List<TbContent> list= (List<TbContent>)this.redisTemplate.boundHashOps("content").get(categoryId);
			if(list == null){
				//根据广告分类ID查询广告列表		
				TbContentExample contentExample=new TbContentExample();
				Criteria criteria2 = contentExample.createCriteria();
				criteria2.andCategoryIdEqualTo(categoryId);
				criteria2.andStatusEqualTo("1");//开启状态
				contentExample.setOrderByClause("sort_order");//排序
				list = this.contentMapper.selectByExample(contentExample);
				//存入缓存
				this.redisTemplate.boundHashOps("content").put(categoryId, list);
			}
			return list;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public void add(TbContent content) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updata(TbContent content) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(TbContent content) {
		// TODO Auto-generated method stub
		
	}

}
