package com.nguyencthi.shopQuanAo.loaiQuanAo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class loaiQuanAoServiceImpl implements loaiQuanAoService{

	@Autowired
	private loaiQuanAoRepository lqaRepo;
	
	@Override
	public Page<loaiQuanAo> findPaginatedLQA(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.lqaRepo.findAll(pageable);
	}
	
}
