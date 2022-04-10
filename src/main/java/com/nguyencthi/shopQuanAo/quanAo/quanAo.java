package com.nguyencthi.shopQuanAo.quanAo;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAo;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAo;
import com.nguyencthi.shopQuanAo.maKhuyenMai.maKhuyenMai;

@Entity
@Table(name = "quanAo")
public class quanAo {
	@Id
	@Column(name = "idQuanAo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idQuanAo;
	private String tenQuanAo;
	private String chiTietQuanAo;
	private Integer giaTien;
	private String urlAnh;
	private String ngayTao;
	private String ngaySua;
	private String ngayXoa;
	@ManyToOne
	@JoinColumn(name = "idMaKhuyenMai")
	private maKhuyenMai maKM;

	@ManyToOne
	@JoinColumn(name = "idHangQA")
	private hangQuanAo hangQA;

	@ManyToOne
	@JoinColumn(name = "idLoaiQA")
	private loaiQuanAo loaiQA;

	public Integer getIdQuanAo() {
		return idQuanAo;
	}

	public void setIdQuanAo(Integer idQuanAo) {
		this.idQuanAo = idQuanAo;
	}

	public String getTenQuanAo() {
		return tenQuanAo;
	}

	public void setTenQuanAo(String tenQuanAo) {
		this.tenQuanAo = tenQuanAo;
	}

	public String getChiTietQuanAo() {
		return chiTietQuanAo;
	}

	public void setChiTietQuanAo(String chiTietQuanAo) {
		this.chiTietQuanAo = chiTietQuanAo;
	}

	public String getUrlAnh() {
		return urlAnh;
	}

	public void setUrlAnh(String urlAnh) {
		this.urlAnh = urlAnh;
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

	public maKhuyenMai getMaKM() {
		return maKM;
	}

	public void setMaKM(maKhuyenMai maKM) {
		this.maKM = maKM;
	}

	public hangQuanAo getHangQA() {
		return hangQA;
	}

	public void setHangQA(hangQuanAo hangQA) {
		this.hangQA = hangQA;
	}

	public loaiQuanAo getLoaiQA() {
		return loaiQA;
	}

	public void setLoaiQA(loaiQuanAo loaiQA) {
		this.loaiQA = loaiQA;
	}

	public Integer getGiaTien() {
		if (this.maKM.getHieuLuc() == 1) {
			if (this.maKM.getPhanTramKhuyenMai() == 0) {
				return giaTien;
			} else {
				int thisKM = this.maKM.getPhanTramKhuyenMai() * giaTien / 100;
				int giaSauKM = giaTien - thisKM;
				return giaSauKM;
			}

		} else {
			return giaTien;
		}
	}

	public Integer getGiagoc() {
		return giaTien;
	}
	
	public void setGiaTien(Integer giaTien) {
		this.giaTien = giaTien;
	}

	public quanAo(Integer idQuanAo, String tenQuanAo, String chiTietQuanAo, Integer giaTien, String urlAnh,
			String ngayTao, String ngaySua, String ngayXoa, maKhuyenMai maKM, hangQuanAo hangQA, loaiQuanAo loaiQA) {
		this.idQuanAo = idQuanAo;
		this.tenQuanAo = tenQuanAo;
		this.chiTietQuanAo = chiTietQuanAo;
		this.giaTien = giaTien;
		this.urlAnh = urlAnh;
		this.ngayTao = ngayTao;
		this.ngaySua = ngaySua;
		this.ngayXoa = ngayXoa;
		this.maKM = maKM;
		this.hangQA = hangQA;
		this.loaiQA = loaiQA;
	}

	public quanAo(Integer idQuanAo) {
		this.idQuanAo = idQuanAo;
	}

	public quanAo() {

	}



}
