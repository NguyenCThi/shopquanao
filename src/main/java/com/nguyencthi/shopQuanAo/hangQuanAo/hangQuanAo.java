package com.nguyencthi.shopQuanAo.hangQuanAo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hangQuanAo")
public class hangQuanAo {
	@Id
	@Column(name = "idHangQA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHangQA;
	private String tenHang;
	private String thuongHieu;
	private String ngayTao;
	private String ngaySua;
	private String ngayXoa;
	private String urlAnh;
	public hangQuanAo() {
			
	}
	
	public hangQuanAo(Integer idHangQA) {
		this.idHangQA = idHangQA;
	}
	
	public hangQuanAo(String tenHang, String thuongHieu, String ngayTao, String ngaySua,
			String ngayXoa, String urlAnh) {
		
		this.tenHang = tenHang;
		this.thuongHieu = thuongHieu;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.ngayXoa = ngayXoa;
		this.urlAnh = urlAnh;
	}
	public Integer getIdHangQA() {
		return idHangQA;
	}
	public void setIdHangQA(Integer idHangQA) {
		this.idHangQA = idHangQA;
	}
	public String getTenHang() {
		return tenHang;
	}
	public void setTenHang(String tenHang) {
		this.tenHang = tenHang;
	}
	public String getThuongHieu() {
		return thuongHieu;
	}
	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
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
	public String getNgayXoa() {
		return ngayXoa;
	}
	public void setNgayXoa(String ngayXoa) {
		this.ngayXoa = ngayXoa;
	}

	public String getUrlAnh() {
		return urlAnh;
	}

	public void setUrlAnh(String urlAnh) {
		this.urlAnh = urlAnh;
	}
}
