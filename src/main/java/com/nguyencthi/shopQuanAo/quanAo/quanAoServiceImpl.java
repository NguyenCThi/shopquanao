package com.nguyencthi.shopQuanAo.quanAo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class quanAoServiceImpl implements quanAoService {
	@Autowired
	private quanAoRepository qaRepo;

	@Override
	public Page<quanAo> findPaginatedTheoLQA(int idLoaiQA, int pageNo, int pageSize, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		return this.qaRepo.getQATheoLoai(idLoaiQA, pageable);
	}

	@Override
	public Page<quanAo> findPaginatedTheoHQA(int idHangQA, int pageNo, int pageSize, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		return this.qaRepo.getQATheoHang(idHangQA, pageable);
	}

	@Override
	public Page<quanAo> findPaginatedMKM(int pageNo, int pageSize, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		return this.qaRepo.getQACoKM(pageable);
	}

	@Override
	public Page<quanAo> timKiemTQA(String keyword, int pageNo, int pageSize, String sortField, String sortDir) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		if (keyword != null) {
			return qaRepo.getTimKiemTQA(keyword, pageable);
		}
		return this.qaRepo.findAll(pageable);
	}

	@Override
	public Page<quanAo> findAllPage(int pageNo, int pageSize, String sortField, String sortDir) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		return this.qaRepo.findAll(pageable);
	}

}
