package com.nguyencthi.shopQuanAo.gioHang;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;

@Entity
@Table(name = "gioHang")
public class gioHang {
	@Id
	@Column(name = "idGioHang")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGioHang;

	private Integer soLuong;
	@ManyToOne
	@JoinColumn(name = "idNguoiDung")
	private nguoiDung NguoiDung;
	@ManyToOne
	@JoinColumn(name = "idQuanAo")
	private quanAo QuanAo;

	public Integer getIdGioHang() {
		return idGioHang;
	}

	public void setIdGioHang(Integer idGioHang) {
		this.idGioHang = idGioHang;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public nguoiDung getNguoiDung() {
		return NguoiDung;
	}

	public void setNguoiDung(nguoiDung nguoiDung) {
		NguoiDung = nguoiDung;
	}

	public quanAo getQuanAo() {
		return QuanAo;
	}

	public void setQuanAo(quanAo quanAo) {
		QuanAo = quanAo;
	}

	@Transient
	public Integer getSubtotal() {
		return this.QuanAo.getGiaTien() * soLuong;
	}
	
	
	
	
	
}
