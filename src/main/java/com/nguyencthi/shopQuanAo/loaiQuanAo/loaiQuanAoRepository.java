package com.nguyencthi.shopQuanAo.loaiQuanAo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface loaiQuanAoRepository extends JpaRepository<loaiQuanAo, Integer> {
	@Query(nativeQuery=true, value="Select * FROM loaiQuanAo ORDER BY RAND() LIMIT 9")
	List<loaiQuanAo> indexShow();
	
	@Query(nativeQuery=true, value="Select * FROM loaiQuanAo ORDER BY RAND() LIMIT 6")
	List<loaiQuanAo> indexShow1();
}
