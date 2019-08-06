package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.query.BrandQuery;
import com.pinyougou.sellergoods.service.BrandService;

import entity.JSONResult;
import entity.PageResult;

/**
 * 品牌列表控制层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
	
	@Reference
	private BrandService brandService;
	 
	/**
	 * 获取所有的品牌列表信息
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		return this.brandService.findAll();
	}
	/**
	 * 返回全部列表
	 * @return
	 */

	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody BrandQuery brandQuery,int page,int rows){		
		
		return brandService.pageList(brandQuery, page, rows);
	}
	/**
	 * 添加 或者删除
	 * @param brand
	 * @return
	 */
	@RequestMapping("/saveOrUpdate")
	public JSONResult saveOrUpdate(@RequestBody TbBrand brand){
		JSONResult json = new JSONResult();
		try {
			this.brandService.seveOrupdate(brand);
			json.setSuccess(true);
			json.setMessage("操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage("提交失败");
		}
		return json;
	}
	/**
	 * 根据id回显信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findId")
	public TbBrand findId(int id){
		
		return this.brandService.findById(id); 
	}
	/**
	 * 批量删除品牌列表信息
	 * @param ids
	 * @return
	 */
	
	@RequestMapping("/bathbrand")
	public JSONResult delById(Long[] ids){
		JSONResult json = new JSONResult();
		try {
			this.brandService.batchDelBrand(ids);
			json.setMessage("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMessage("删除失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		
		return this.brandService.selectOptionList();
	}

}
