package com.nguyencthi.shopQuanAo.hangQuanAo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nguyencthi.shopQuanAo.quanAo.quanAo;
import com.nguyencthi.shopQuanAo.quanAo.quanAoRepository;

@Service
public class hangQuanAoServiceImpl implements hangQuanAoService{
	@Autowired
	private hangQuanAoRepository hqaRepo;

	@Override
	public Page<hangQuanAo> findPaginatedTheoHQA(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.hqaRepo.findAll(pageable);
	}

}
