package com.pinyougou.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbGoods;
import com.pinyougou.pojogroup.Goods;
import com.pinyougou.query.GoodsQuery;
import com.pinyougou.sellergoods.service.GoodsService;

import entity.JSONResult;
import entity.PageResult;

/**
 * 商品添加控制成
 * 
 * @author 89568
 *
 */

@RestController
@RequestMapping("/goods")
public class GoodsController {
	@Reference
	private GoodsService goodsService;

	@RequestMapping("/add")
	public JSONResult add(@RequestBody Goods goods) {
		JSONResult json = new JSONResult();
		try {
			String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
			goods.getGoods().setSellerId(sellerId);
			this.goodsService.add(goods);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("添加失败");
			json.setSuccess(false);
		}

		return json;
	}

	@RequestMapping("/search")
	public PageResult search(@RequestBody GoodsQuery query, int page, int size) {
		// 获取商家ID
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		// 添加查询条件
		
		query.setSellerId(sellerId);

		return this.goodsService.findPage(query, page, size);
	}
	
	@RequestMapping("/shelves")
	public JSONResult updataMarketable(Long[] id,String MarkeTable){
		JSONResult json = new JSONResult();
		try {
			this.goodsService.shelves(id, MarkeTable);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("失败");
		}
		return json;
	}
}
