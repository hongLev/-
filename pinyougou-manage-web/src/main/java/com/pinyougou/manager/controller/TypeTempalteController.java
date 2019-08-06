package com.pinyougou.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
/**
 * 模板controller层
 * @author 89568
 *
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.query.TypeTempalteQuery;
import com.pinyougou.sellergoods.service.TypeTempalteService;

import entity.JSONResult;
import entity.PageResult;
@RestController
@RequestMapping("/TypeTempalte")
public class TypeTempalteController {

	@Reference
	private TypeTempalteService typeTempalteService;
	
	/**
	 * 分页展示
	 * @param query
	 * @param num
	 * @param size
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody TypeTempalteQuery query,int num , int size){
		return this.typeTempalteService.pageList(query, num, size);
	}
	/**
	 * 新增修改共通方法
	 * @param typeTemplate
	 * @return
	 */
	@RequestMapping("/seavOfupdata")
	public JSONResult seavOfupdata(@RequestBody TbTypeTemplate typeTemplate){
		System.out.println(typeTemplate.getId());
		JSONResult json = new JSONResult();
		try {
			this.typeTempalteService.seavOfupdata(typeTemplate);
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
	 * 查找对象
	 * @param id
	 * @return
	 */
	@RequestMapping("/findId")
	public TbTypeTemplate findId(Long id){
		
		return this.typeTempalteService.findOne(id);
	}
	
	@RequestMapping("/deleteType")
	public JSONResult deleteType(Long[] id){
		JSONResult json = new JSONResult();
		try {
			this.typeTempalteService.deleteById(id);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("删除失败，请重试");
			json.setSuccess(false);
		}
		return json;
	}
	
	
}
