package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.query.SpecificationQuery;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.JSONResult;
import entity.PageResult;

@RestController
@RequestMapping("/Specification")
public class SpecificationController {
	
	@Reference
	private SpecificationService specificationService;
	/**
	 * 返回分页查询列表
	 * @param query
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody SpecificationQuery query,int page,int rows){
		
		return this.specificationService.PageResult(query, page, rows);
	}
	/**
	 * 修改新增 共通Controller
	 * @param specification
	 * @return
	 */
	@RequestMapping("/saveOrupdate")
	public JSONResult saveOrupdate(@RequestBody Specification specification){
		JSONResult json = new JSONResult();
		try {
			this.specificationService.seavOrupdata(specification);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("保存失败");
		}
		return json;
	}
	/**
	 * 根据id查找Specification
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Specification findOne(Long id){
		return this.specificationService.findOne(id);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delbyid")
	public JSONResult delByid(Long[] id){
		JSONResult json = new JSONResult();
		try {
			this.specificationService.delById(id);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("删除失败");
		}
		return json;
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	@RequestMapping("/selectSpecificationList")
	public List<Map> selectSpecificationList(){
		
		return this.specificationService.selectSpecificationList();
	}
			
}
