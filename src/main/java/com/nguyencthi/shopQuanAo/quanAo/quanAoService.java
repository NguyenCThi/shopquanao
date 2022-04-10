package com.nguyencthi.shopQuanAo.quanAo;

import org.springframework.data.domain.Page;

public interface quanAoService {
	Page<quanAo> findPaginatedTheoLQA(int idLoaiQA, int pageNo, int pageSize, String sortField, String sortDir);
	
	Page<quanAo> findPaginatedTheoHQA(int idHangQA, int pageNo, int pageSize, String sortField, String sortDir);
	
	Page<quanAo> findPaginatedMKM(int pageNo, int pageSize, String sortField, String sortDir);
	
	Page<quanAo> timKiemTQA(String keyword, int pageNo, int pageSize, String sortField, String sortDir);
	
	Page<quanAo> findAllPage(int pageNo, int pageSize, String sortField, String sortDir);
}
