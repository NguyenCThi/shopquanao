package com.nguyencthi.shopQuanAo.nguoiDung;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomNguoiDungDetails implements UserDetails {

	private nguoiDung nd;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return nd.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nd.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return nd.isActive();
	}

	public CustomNguoiDungDetails(nguoiDung nd) {
		
		this.nd = nd;
	}

}
