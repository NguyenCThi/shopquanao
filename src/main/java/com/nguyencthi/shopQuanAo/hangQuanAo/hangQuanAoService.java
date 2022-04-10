package com.nguyencthi.shopQuanAo.hangQuanAo;

import org.springframework.data.domain.Page;

import com.nguyencthi.shopQuanAo.quanAo.quanAo;

public interface hangQuanAoService {
	Page<hangQuanAo> findPaginatedTheoHQA(int pageNo, int pageSize);
}
