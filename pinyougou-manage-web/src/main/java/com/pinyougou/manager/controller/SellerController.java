package com.pinyougou.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.query.SellerQuery;
import com.pinyougou.sellergoods.service.SellerService;

import entity.JSONResult;
import entity.PageResult;

/**
 * 商家后台审核控制器
 * @author 89568
 *
 */

@RestController
@RequestMapping("/SellerBankend")
public class SellerController {
	
	@Reference
	private SellerService sellerService;
	/**
	 * 分页查询
	 * @param query
	 * @param num
	 * @param size
	 * @return
	 */
	@RequestMapping("/pageList")
	public PageResult pageList(@RequestBody SellerQuery query,int num,int size){
		return this.sellerService.pageList(query, num, size);
	}
	/**
	 * 根据主键Id查找
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbSeller findOne(String sellerId){
		return this.sellerService.findSellerById(sellerId);
	}
	/**
	 * 根据主键id和状态
	 * @param sellerId
	 * @param start
	 * @return
	 */
	@RequestMapping("/updataStart")
	public JSONResult updataStart(String sellerId,String start){
		JSONResult json = new JSONResult();
		try {
			this.sellerService.updataSellerStart(sellerId, start);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("审核失败，请稍后重试");
		}
		return json;
	}
}
