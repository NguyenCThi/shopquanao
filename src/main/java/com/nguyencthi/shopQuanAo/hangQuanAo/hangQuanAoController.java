package com.nguyencthi.shopQuanAo.hangQuanAo;

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
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAo;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAoRepository;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDungRepository;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;
import com.nguyencthi.shopQuanAo.quanAo.quanAoRepository;
import com.nguyencthi.shopQuanAo.quanAo.quanAoServiceImpl;

@Controller
public class hangQuanAoController {

	@Autowired
	private hangQuanAoRepository repo;
	@Autowired
	private quanAoRepository repoQA;
	@Autowired
	private hangQuanAoServiceImpl hqaService;
	@Autowired
	private quanAoServiceImpl qaService;
	@Autowired
	private HttpSession session;
	@Autowired
	private nguoiDungRepository repoND;
	@Autowired
	private gioHangRepository cartRepo;
    @Autowired 
    private loaiQuanAoRepository lqaRepo;

	@GetMapping("/hang-quan-ao")
	public String listCategories(Model model) {
		return findPaginatedHQA(1, model);
	}

	@GetMapping("/hang-quan-ao/page/{pageNo}")
	public String findPaginatedHQA(@PathVariable(value = "pageNo") int pageNo, Model model) {

		// Cập nhập giỏ hàng
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			List<gioHangSession> cartItems = (ArrayList<gioHangSession>) session.getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<gioHangSession>();
			}
			model.addAttribute("cartItems", cartItems);
			List<hangQuanAo> listHQAInNav = repo.indexShow();
            List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
            model.addAttribute("listLQAInNav", listLQAInNav);
            model.addAttribute("listHQAInNav", listHQAInNav);
		} else {
			nguoiDung nguoiDungODAY = repoND.findByEmail(test);
			List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
			model.addAttribute("cartItems", cartItems);
			List<hangQuanAo> listHQAInNav = repo.indexShow();
            List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
            model.addAttribute("listLQAInNav", listLQAInNav);
            model.addAttribute("listHQAInNav", listHQAInNav);
		}

		int pageSize = 8;
		Page<hangQuanAo> page = hqaService.findPaginatedTheoHQA(pageNo, pageSize);
		List<hangQuanAo> listHQA = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listHQA", listHQA);
		return "hang_quan_ao";
	}

	@GetMapping("/hang-quan-ao/{idHangQA}")
	public String getAllHangProductQA(@PathVariable(value = "idHangQA") int idHangQA, Model model) {
		return findPaginatedTheoHQA(idHangQA, 1, model, "giaTien", "asc");
	}

	@GetMapping("/hang-quan-ao/{idHangQA}/{pageNo}")
	public String findPaginatedTheoHQA(@PathVariable(value = "idHangQA") int idHangQA,
			@PathVariable(value = "pageNo") int pageNo, Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		// Cập nhập giỏ hàng
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			List<gioHangSession> cartItems = (ArrayList<gioHangSession>) session.getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<gioHangSession>();
			}
			model.addAttribute("cartItems", cartItems);
			List<hangQuanAo> listHQAInNav = repo.indexShow();
            List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
            model.addAttribute("listLQAInNav", listLQAInNav);
            model.addAttribute("listHQAInNav", listHQAInNav);
		} else {
			nguoiDung nguoiDungODAY = repoND.findByEmail(test);
			List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
			model.addAttribute("cartItems", cartItems);
			List<hangQuanAo> listHQAInNav = repo.indexShow();
            List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
            model.addAttribute("listLQAInNav", listLQAInNav);
            model.addAttribute("listHQAInNav", listHQAInNav);
		}

		int pageSize = 8;
		Page<quanAo> page = qaService.findPaginatedTheoHQA(idHangQA, pageNo, pageSize, sortField, sortDir);
		List<quanAo> listQA = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listQA", listQA);
		model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    
		return "qaTheoHangQA";
	}

}
