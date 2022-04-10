package com.nguyencthi.shopQuanAo.hoaDon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface hoaDonRepository extends JpaRepository<hoaDon, Integer> {
	@Query(nativeQuery=true, value="Select * FROM hoaDon Where idNguoiDung=?1")
	public List<hoaDon> findByNguoiDung(Integer idNguoiDung);
}
