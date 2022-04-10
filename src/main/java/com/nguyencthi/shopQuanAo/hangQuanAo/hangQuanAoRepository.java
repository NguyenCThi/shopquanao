package com.nguyencthi.shopQuanAo.hangQuanAo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface hangQuanAoRepository extends JpaRepository<hangQuanAo, Integer> {
	@Query(nativeQuery=true, value="Select * FROM hangQuanAo ORDER BY RAND() LIMIT 6")
	List<hangQuanAo> indexShow();
}
