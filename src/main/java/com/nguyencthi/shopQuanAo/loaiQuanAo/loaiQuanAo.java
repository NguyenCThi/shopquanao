package com.nguyencthi.shopQuanAo.loaiQuanAo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loaiQuanAo")
public class loaiQuanAo {
	@Id
	@Column(name = "idLoaiQA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLoaiQA;
	private String loaiQuanAo;
	private String urlAnh;

	public Integer getIdLoaiQA() {
		return idLoaiQA;
	}

	public void setIdLoaiQA(Integer idLoaiQA) {
		this.idLoaiQA = idLoaiQA;
	}

	public String getLoaiQuanAo() {
		return loaiQuanAo;
	}

	public void setLoaiQuanAo(String loaiQuanAo) {
		this.loaiQuanAo = loaiQuanAo;
	}

	public loaiQuanAo(Integer idLoaiQA) {
		this.idLoaiQA = idLoaiQA;
	}

	public loaiQuanAo(String loaiQuanAo, String urlAnh) {
		this.loaiQuanAo = loaiQuanAo;
		this.urlAnh = urlAnh;
	}

	public loaiQuanAo() {

	}

	public String getUrlAnh() {
		return urlAnh;
	}

	public void setUrlAnh(String urlAnh) {
		this.urlAnh = urlAnh;
	}
}
