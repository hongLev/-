package com.pinyougou.shop.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

import entity.JSONResult;

/**
 * 商家控制层
 * @author 89568
 *
 */

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Reference
	private SellerService sellerService;
	
	
	
	@RequestMapping("/add")
	public JSONResult add(@RequestBody TbSeller seller){
		JSONResult json = new JSONResult();
		try {
			this.sellerService.insert(seller);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("提交失败，请稍后重试");
			
		}
		return json;
	}
	
	
}
