package com.nguyencthi.shopQuanAo.nguoiDung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomNguoiDungDetailsService implements UserDetailsService {
	@Autowired
	private nguoiDungRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		nguoiDung nd = repo.findByEmail(email);
		if(nd != null && nd.isActive()) {
			return new CustomNguoiDungDetails(nd);
			
		}else {
			throw new UsernameNotFoundException("Email not found");
		}
		
	}

}
