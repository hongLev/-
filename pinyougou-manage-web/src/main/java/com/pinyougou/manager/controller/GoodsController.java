package com.pinyougou.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.query.GoodsQuery;
import com.pinyougou.sellergoods.service.GoodsService;

import entity.JSONResult;
import entity.PageResult;

/**
 * 商品审核控制层
 * @author 89568
 *
 */

@RestController
@RequestMapping("/goodss")
public class GoodsController {

	@Reference
	private GoodsService goodService;
	
	@RequestMapping("/pageList")
	public PageResult pageList(@RequestBody GoodsQuery query,int page,int size){
		query.setStatus("0");
		return this.goodService.findPage(query, page, size);
	}
	
	//审核商品
	@RequestMapping("/updataStatus")
	public JSONResult updataStatus(Long[] id,String status){
		JSONResult json = new JSONResult();
		try {
			this.goodService.auditStatus(id, status);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("审核失败，稍后再试");
			json.setSuccess(true);
		}
		return json;
	}
	
	//删除商品
	
	@RequestMapping("/deleteGoods")
	public JSONResult deleteGoods(Long[] id){
		JSONResult json = new JSONResult();
		try {
			this.goodService.deleteGoods(id);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("删除失败");
			json.setSuccess(false);
		}
		return json;
	}
}
