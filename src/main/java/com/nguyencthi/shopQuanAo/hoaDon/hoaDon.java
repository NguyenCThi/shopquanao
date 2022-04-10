package com.nguyencthi.shopQuanAo.hoaDon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;

@Entity
@Table(name = "hoaDon")
public class hoaDon {
	@Id
	@Column(name = "idHoaDon")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHoaDon;
	private String ngayTao;
	private String ngaySua;
	private String ngayXoa;
	private String diaChiNhanHang;
	private Integer trangThai;
	@ManyToOne
	@JoinColumn(name = "idNguoiDung")
	private nguoiDung NguoiDung;

	public Integer getIdHoaDon() {
		return idHoaDon;
	}

	public void setIdHoaDon(Integer idHoaDon) {
		this.idHoaDon = idHoaDon;
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

	public nguoiDung getNguoiDung() {
		return NguoiDung;
	}

	public void setNguoiDung(nguoiDung nguoiDung) {
		NguoiDung = nguoiDung;
	}

	public hoaDon(Integer idHoaDon, String ngayTao, String ngaySua, String ngayXoa, String diaChiNhanHang, Integer trangThai, nguoiDung nguoiDung) {
		this.idHoaDon = idHoaDon;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.ngayXoa = ngayXoa;
		this.diaChiNhanHang = diaChiNhanHang;
		this.trangThai = trangThai;
		NguoiDung = nguoiDung;
	}
	
	public hoaDon() {
		
	}
	
	public hoaDon(Integer idHoaDon) {
		this.idHoaDon = idHoaDon;
	}

	public String getDiaChiNhanHang() {
		return diaChiNhanHang;
	}

	public void setDiaChiNhanHang(String diaChiNhanHang) {
		this.diaChiNhanHang = diaChiNhanHang;
	}

	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}
}
