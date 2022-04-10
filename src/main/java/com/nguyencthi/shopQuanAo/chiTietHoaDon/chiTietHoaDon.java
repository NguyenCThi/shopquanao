package com.nguyencthi.shopQuanAo.chiTietHoaDon;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nguyencthi.shopQuanAo.hoaDon.hoaDon;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;

@Entity
@Table(name = "chiTietHoaDon")
public class chiTietHoaDon {
	@Id
	@Column(name = "idCTHD")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCTHD;
	@ManyToOne
	@JoinColumn(name = "idHoaDon")
	private hoaDon HoaDon;
	@ManyToOne
	@JoinColumn(name = "idQuanAo")
	private quanAo QuanAo;
	private Integer soLuong;

	public Integer getIdCTHD() {
		return idCTHD;
	}

	public void setIdCTHD(Integer idCTHD) {
		this.idCTHD = idCTHD;
	}

	public hoaDon getHoaDon() {
		return HoaDon;
	}

	public void setHoaDon(hoaDon hoaDon) {
		HoaDon = hoaDon;
	}

	public quanAo getQuanAo() {
		return QuanAo;
	}

	public void setQuanAo(quanAo quanAo) {
		QuanAo = quanAo;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public chiTietHoaDon(Integer idCTHD, hoaDon hoaDon, quanAo quanAo, Integer soLuong) {
		this.idCTHD = idCTHD;
		HoaDon = hoaDon;
		QuanAo = quanAo;
		this.soLuong = soLuong;
	}

	
	public chiTietHoaDon(Integer idCTHD) {
		this.idCTHD = idCTHD;
	}

	public chiTietHoaDon() {

	}
	
	@Transient
	public Integer getSubtotal() {
		return this.QuanAo.getGiaTien() * soLuong;
	}
	
	
}
