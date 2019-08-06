package com.pinyougou.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.ItemCatService;

import entity.JSONResult;

@RestController
@RequestMapping("/ItemCat")
public class ItemCatController {
	@Reference
	private ItemCatService itemCatService;
	
	/**
	 * 根据父类id获取商品分类
	 * @param id
	 * @return
	 */
	@RequestMapping("/select")
	public List<TbItemCat> getItemCatByParenId(Long id){
		return this.itemCatService.getItemCatByparenId(id);
	}
	/**
	 * 新增 或者修改
	 * @param itemCat
	 */
	@RequestMapping("/addOrupdata")
	public JSONResult addOrupdata(@RequestBody TbItemCat itemCat){
		JSONResult json = new JSONResult();
		try {
			this.itemCatService.addOrupdata(itemCat);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("保存失败");
			json.setSuccess(false);
		}
		return json;
	}
	/**
	 * 根据主键id查找
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbItemCat findOne(Long id){
		
		return this.itemCatService.findOne(id);
	}
	/**
	 * 根据主键id依此删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	public JSONResult deleteById(Long[] id){
		JSONResult json = new JSONResult();
		try {
			this.itemCatService.deleteItemCatById(id);
			json.setSuccess(true);
			json.setMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("删除失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 查找全部
	 * @return
	 */
	@RequestMapping("/selectAll")
	public List<TbItemCat> selectAll(){
		return this.itemCatService.findAll();
	}
}
