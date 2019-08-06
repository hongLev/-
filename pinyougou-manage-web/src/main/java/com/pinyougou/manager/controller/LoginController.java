package com.pinyougou.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登入控制器
 * @author 89568
 *
 */

@RestController
@RequestMapping("/login")
public class LoginController {

	/**
	 * 
	 * 显示登入成功的用户名
	 * @return
	 */
	@RequestMapping("/showName")
	public Map showLoginName(){
		//返回上下文的名字  获取认证成功的用户名
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(login);
		Map<String,String> map= new HashMap<String,String>();
		//存储登入名
		map.put("loginName", login);
		return map;
	}
}
