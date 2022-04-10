package com.nguyencthi.shopQuanAo.nguoiDung;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Table;


@Entity
@Table(name = "nguoiDung")
public class nguoiDung {
	@Id
	@Column(name = "idNguoiDung")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNguoiDung;
	private String email;

	private String password;

	private String hoTen;

	private String soDienThoai;

	private String verifyCode;

	private  boolean active;

	private String ngayTao;

	private String ngaySua;
	
	private Integer roles;

	public nguoiDung() {
		
	}
	
	public nguoiDung(Integer idNguoiDung, String email, String password, String hoTen, String soDienThoai,
			String verifyCode, boolean active, String ngayTao, String ngaySua,
			Integer roles) {
		this.idNguoiDung = idNguoiDung;
		this.email = email;
		this.password = password;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.verifyCode = verifyCode;
		this.active = active;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.roles = roles;
	}

	public Integer getIdNguoiDung() {
		return idNguoiDung;
	}

	public void setIdNguoiDung(Integer idNguoiDung) {
		this.idNguoiDung = idNguoiDung;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getNgaySua() {
		return ngaySua;
	}

	public void setNgaySua(String ngaySua) {
		this.ngaySua = ngaySua;
	}

	public Integer getRoles() {
		return roles;
	}

	public void setRoles(Integer roles) {
		this.roles = roles;
	}
	

	

	
	
	
}
