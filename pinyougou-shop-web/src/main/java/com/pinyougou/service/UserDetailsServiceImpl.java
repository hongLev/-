package com.pinyougou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
/**
 * 登入权限的service
 * @author 89568
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private SellerService sellerService;
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	
	/**
	 * 根据用户名加载用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		TbSeller loginUser = this.sellerService.findSellerById(username);
		//构建角色信息
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		if(loginUser!=null){
			if(loginUser.getStatus() .equals("1")){
				return new User(loginUser.getSellerId(),loginUser.getPassword(),grantedAuthority);
			}
			return null;
		}
		return null;
		
		
	}

}
