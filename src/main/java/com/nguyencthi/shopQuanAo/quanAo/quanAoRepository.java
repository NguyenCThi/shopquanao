package com.nguyencthi.shopQuanAo.quanAo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface quanAoRepository extends JpaRepository<quanAo, Integer> {
	@Query(nativeQuery=true, value="Select * FROM quanAo ORDER BY RAND() LIMIT 12")
	List<quanAo> indexShow();
	
	
	
	@Query(nativeQuery=true, value="Select * FROM quanAo ORDER BY RAND() LIMIT 3")
	List<quanAo> get3RandomItem();
	
	@Query(nativeQuery=true, value="Select * FROM quanAo WHERE idHangQA=?1")
	List<quanAo> getQATheoHang(Integer idHangQA);
	
	@Query(nativeQuery=true, value="Select * FROM quanAo WHERE idLoaiQA=?1")
	Page<quanAo> getQATheoLoai(Integer idLoaiQA, Pageable pageable);
	
	@Query(nativeQuery=true, value="Select * FROM quanAo WHERE idHangQA=?1")
	Page<quanAo> getQATheoHang(Integer idHangQA, Pageable pageable);
	
	@Query(nativeQuery=true, value="Select * FROM quanAo INNER JOIN maKhuyenMai ON quanAo.idMaKhuyenMai=maKhuyenMai.idMaKhuyenMai WHERE phanTramKhuyenMai > 0 ORDER BY RAND() LIMIT 5")
	List<quanAo> get5QACoKhuyenMai();
	
	@Query(nativeQuery=true, value="Select * FROM quanAo qa INNER JOIN maKhuyenMai mkm ON qa.idMaKhuyenMai=mkm.idMaKhuyenMai WHERE mkm.phanTramKhuyenMai > 0")
	Page<quanAo> getQACoKM(Pageable pageable);
	
	@Query(nativeQuery=true, value="Select * FROM quanAo WHERE tenQuanAo LIKE %?1%")
	Page<quanAo> getTimKiemTQA(String keyword, Pageable pageable);
	
}
	