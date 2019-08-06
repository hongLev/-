package com.pinyougou.portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentSercvice;
import com.pinyougou.pojo.TbContent;

@RestController
@RequestMapping("/content")
public class ContentController {

	@Reference
	private ContentSercvice contentService;
	
	/**
	 * 根据广告分类ID查询广告列表
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/findByCategoryId")
	public List<TbContent> findByCategoryId(Long categoryId) {
		return this.contentService.findByCategoryId(categoryId);
	}
}