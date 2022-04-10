package com.nguyencthi.shopQuanAo.loaiQuanAo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nguyencthi.shopQuanAo.gioHang.gioHang;
import com.nguyencthi.shopQuanAo.gioHang.gioHangRepository;
import com.nguyencthi.shopQuanAo.gioHang.gioHangSession;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAo;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAoRepository;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDungRepository;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;
import com.nguyencthi.shopQuanAo.quanAo.quanAoServiceImpl;

@Controller
public class loaiQuanAoController {

	@Autowired
	private loaiQuanAoServiceImpl lqaService;

	@Autowired
	private quanAoServiceImpl qaInLQAService;
	@Autowired
	private nguoiDungRepository repoND;
	@Autowired
	private gioHangRepository cartRepo;
	@Autowired
	private HttpSession session;
	@Autowired
	private hangQuanAoRepository hqaRepo;
	@Autowired
	private loaiQuanAoRepository lqaRepo;

	@GetMapping("/loai-quan-ao")
	public String listCategories(Model model) {
		return findPaginatedLQA(1, model);
	}

	@GetMapping("/loai-quan-ao/page/{pageNo}")
	public String findPaginatedLQA(@PathVariable(value = "pageNo") int pageNo, Model model) {

		// Cập nhập giỏ hàng
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			List<gioHangSession> cartItems = (ArrayList<gioHangSession>) session.getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<gioHangSession>();
			}
			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
			List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
			model.addAttribute("listLQAInNav", listLQAInNav);
			model.addAttribute("listHQAInNav", listHQAInNav);
			model.addAttribute("cartItems", cartItems);
		} else {
			nguoiDung nguoiDungODAY = repoND.findByEmail(test);
			List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
			List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
			model.addAttribute("listLQAInNav", listLQAInNav);
			model.addAttribute("listHQAInNav", listHQAInNav);
			model.addAttribute("cartItems", cartItems);
		}

		int pageSize = 8;
		Page<loaiQuanAo> page = lqaService.findPaginatedLQA(pageNo, pageSize);
		List<loaiQuanAo> listLQA = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listLQA", listLQA);
		return "categories";
	}

	@GetMapping("/loai-quan-ao/{idLoaiQA}")
	public String listCategories(@PathVariable(value = "idLoaiQA") int idLoaiQA, Model model) {
		return findPaginatedTheoLQA(idLoaiQA, 1, model, "giaTien", "asc");
	}

	@GetMapping("/loai-quan-ao/{idLoaiQA}/{pageNo}")
	public String findPaginatedTheoLQA(@PathVariable(value = "idLoaiQA") int idLoaiQA,
			@PathVariable(value = "pageNo") int pageNo, Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		// Cập nhập giỏ hàng
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			List<gioHangSession> cartItems = (ArrayList<gioHangSession>) session.getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<gioHangSession>();
			}
			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
			List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
			model.addAttribute("listLQAInNav", listLQAInNav);
			model.addAttribute("listHQAInNav", listHQAInNav);
			model.addAttribute("cartItems", cartItems);
		} else {
			nguoiDung nguoiDungODAY = repoND.findByEmail(test);
			List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
			List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
			model.addAttribute("listLQAInNav", listLQAInNav);
			model.addAttribute("listHQAInNav", listHQAInNav);
			model.addAttribute("cartItems", cartItems);
		}

		int pageSize = 8;
		Page<quanAo> page = qaInLQAService.findPaginatedTheoLQA(idLoaiQA, pageNo, pageSize, sortField, sortDir);
		List<quanAo> listQA = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listQA", listQA);
		model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		return "qaTheoLoaiQA";
	}

}
