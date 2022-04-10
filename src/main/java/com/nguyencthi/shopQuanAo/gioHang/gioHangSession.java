package com.nguyencthi.shopQuanAo.gioHang;

import java.beans.Transient;

public class gioHangSession {
	private int idQuanAo;
	private String tenQuanAo;
	private Integer giaTien;
	private int soLuong;
	private String urlAnh;
	private int phanTramKhuyenMai;

	public int getIdQuanAo() {
		return idQuanAo;
	}

	public void setIdQuanAo(int idQuanAo) {
		this.idQuanAo = idQuanAo;
	}

	public String getTenQuanAo() {
		return tenQuanAo;
	}

	public void setTenQuanAo(String tenQuanAo) {
		this.tenQuanAo = tenQuanAo;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getUrlAnh() {
		return urlAnh;
	}

	public void setUrlAnh(String urlAnh) {
		this.urlAnh = urlAnh;
	}

	public int getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}

	public void setPhanTramKhuyenMai(int phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}
	@Transient
	public Integer getGiagoc() {
		return giaTien;
	}
	public Integer getGiaTien() {
		if (this.getPhanTramKhuyenMai() == 0) {
			return giaTien;
		} else {
			int giaKM = giaTien * this.getPhanTramKhuyenMai() / 100;
			int giaNow = giaTien - giaKM;
			return giaNow;
		}
	}

	public void setGiaTien(Integer giaTien) {
		this.giaTien = giaTien;
	}

	

	@Transient
	public Integer getSubtotal() {
		return this.getGiaTien() * soLuong;
	}
}
