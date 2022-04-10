package com.nguyencthi.shopQuanAo.chiTietHoaDon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface chiTietHoaDonRepository extends JpaRepository<chiTietHoaDon, Integer> {
	
	@Query(nativeQuery=true, value="Select * FROM chiTietHoaDon Where idHoaDon=?1")
	public List<chiTietHoaDon> findByIdHD(Integer idHoaDon);
}
