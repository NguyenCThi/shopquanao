package com.nguyencthi.shopQuanAo.loaiQuanAo;

import org.springframework.data.domain.Page;

public interface loaiQuanAoService {
	Page<loaiQuanAo> findPaginatedLQA(int pageNo, int pageSize);
}
