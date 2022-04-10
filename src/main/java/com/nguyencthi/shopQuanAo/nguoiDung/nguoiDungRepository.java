package com.nguyencthi.shopQuanAo.nguoiDung;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface nguoiDungRepository extends JpaRepository<nguoiDung, Integer> {
	@Query("Select u FROM nguoiDung u WHERE u.email = ?1")
	nguoiDung findByEmail(String email);
	
	@Query("Select u FROM nguoiDung u Where u.email = ?1 AND u.password = ?2")
	nguoiDung findByPassword(String email, String password);
}
