package com.nguyencthi.shopQuanAo.maKhuyenMai;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maKhuyenMai")
public class maKhuyenMai {
	@Id
	@Column(name = "idMaKhuyenMai")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idMaKhuyenMai;
	private String tenKhuyenMai;
	private String chiTietKhuyenMai;
	private Integer phanTramKhuyenMai;
	private Integer hieuLuc;
	private String ngayTao;
	private String ngaySua;
	private String ngayXoa;
	public maKhuyenMai(String idMaKhuyenMai, String tenKhuyenMai, String chiTietKhuyenMai, Integer phanTramKhuyenMai,
			Integer hieuLuc, String ngayTao, String ngaySua, String ngayXoa) {
		
		this.idMaKhuyenMai = idMaKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.chiTietKhuyenMai = chiTietKhuyenMai;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
		this.hieuLuc = hieuLuc;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.ngayXoa = ngayXoa;
	}
	
	public maKhuyenMai(String idMaKhuyenMai) {
		this.idMaKhuyenMai = idMaKhuyenMai;
		
	}
	
	public maKhuyenMai() {
		
		
	}

	public String getIdMaKhuyenMai() {
		return idMaKhuyenMai;
	}

	public void setIdMaKhuyenMai(String idMaKhuyenMai) {
		this.idMaKhuyenMai = idMaKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getChiTietKhuyenMai() {
		return chiTietKhuyenMai;
	}

	public void setChiTietKhuyenMai(String chiTietKhuyenMai) {
		this.chiTietKhuyenMai = chiTietKhuyenMai;
	}

	public Integer getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}

	public void setPhanTramKhuyenMai(Integer phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	public Integer getHieuLuc() {
		return hieuLuc;
	}

	public void setHieuLuc(Integer hieuLuc) {
		this.hieuLuc = hieuLuc;
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
	
	
}
