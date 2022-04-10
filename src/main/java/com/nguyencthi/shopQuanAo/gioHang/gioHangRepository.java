package com.nguyencthi.shopQuanAo.gioHang;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;


@Repository
public interface gioHangRepository extends JpaRepository<gioHang, Integer> {

	@Query(nativeQuery=true, value="Select * FROM gioHang Where idNguoiDung=?1")
	public List<gioHang> findByNguoiDung(Integer idNguoiDung);
	
	@Query(nativeQuery=true, value="Select * FROM gioHang Where idNguoiDung=?1 AND idQuanAo=?2")
	gioHang findByNguoiDungAndQuanAo(Integer idNguoiDung, Integer idQuanAo);
	
	@Query(nativeQuery=true, value="Update gioHang SET soLuong=?1 Where idNguoiDung=?2 AND idQuanAo=?3")
	@Modifying
	public void updateQuantity(Integer soLuong, Integer idNguoiDung, Integer idQuanAo);
	
	@Query(nativeQuery=true, value="DELETE FROM gioHang Where idNguoiDung=?1 AND idQuanAo=?2")
	@Modifying
	public void deleteByNguoiDungAndQuanAo(Integer idNguoiDung, Integer idQuanAo);
	
}
