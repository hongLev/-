package com.pinyougou.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.sellergoods.service.ContentService;

import entity.PageResult;

/**
 * 广告管理控制层
 */

@RestController
@RequestMapping("/contnet")
public class ContentController {
	@Reference
	private ContentService contentService;
	
	/**
	 * 分页显示
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/pageList")
	public PageResult pageList(int page,int size){
		return this.contentService.PageList(page, size);
	}
}
