package com.nguyencthi.shopQuanAo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nguyencthi.shopQuanAo.gioHang.gioHang;
import com.nguyencthi.shopQuanAo.gioHang.gioHangRepository;
import com.nguyencthi.shopQuanAo.gioHang.gioHangSession;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAo;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAoRepository;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAo;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAoRepository;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDungRepository;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;
import com.nguyencthi.shopQuanAo.quanAo.quanAoRepository;
import com.nguyencthi.shopQuanAo.quanAo.quanAoServiceImpl;

@Controller
@SessionAttributes("gioHang")
public class MainController {
	@Autowired
	private quanAoRepository repoQA;
	@Autowired
	private nguoiDungRepository repo;
	@Autowired
	private gioHangRepository cartRepo;
	@Autowired
	private HttpSession session;
	@Autowired
	private hangQuanAoRepository hqaRepo;
	@Autowired
	private loaiQuanAoRepository lqaRepo;
	@Autowired
	private quanAoServiceImpl qaService;

	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public String viewHomePage(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			List<gioHangSession> cartItems = (ArrayList<gioHangSession>) session.getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<gioHangSession>();
			}
			List<quanAo> listQA = repoQA.indexShow();
			List<hangQuanAo> listHQA = hqaRepo.indexShow();
			List<loaiQuanAo> listLQA = lqaRepo.indexShow();
			List<quanAo> listQACoKhuyenMai = repoQA.get5QACoKhuyenMai();
			model.addAttribute("listQACoKhuyenMai", listQACoKhuyenMai);
			model.addAttribute("listLQA", listLQA);
			model.addAttribute("listHQA", listHQA);
			model.addAttribute("cartItems", cartItems);
			model.addAttribute("listQA", listQA);
		} else {
			nguoiDung nguoiDungODAY = repo.findByEmail(test);
			List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
			List<quanAo> listQA = repoQA.indexShow();
			List<hangQuanAo> listHQA = hqaRepo.indexShow();
			List<loaiQuanAo> listLQA = lqaRepo.indexShow();
			model.addAttribute("listHQA", listHQA);
			model.addAttribute("listLQA", listLQA);
			model.addAttribute("listQA", listQA);
			model.addAttribute("cartItems", cartItems);
		}

		return "index";

	}

	@GetMapping("/dang-nhap")
	public String formDangNhapDangKy(Model model, nguoiDung nd) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();

		if (test != "anonymousUser") {
			return "redirect:/";
		} else {
			model.addAttribute("nguoiDung", new nguoiDung());
			return "loginRegister";
		}

	}

	@PostMapping("/process_register")
	public String processRegistration(nguoiDung nd, ModelMap model) {
		if (repo.findByEmail(nd.getEmail()) != null) {
			model.addAttribute("ERROR", "Tài khoản đã tồn tại!");
			return "loginRegister";
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String rawPassword = nd.getPassword();
			String encodedPassword = encoder.encode(rawPassword);
			nd.setPassword(encodedPassword);
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String datehere = date.format(formatter);
			nd.setActive(true);
			nd.setNgayTao(datehere);
			nd.setRoles(1);
			repo.save(nd);
			return "register_success";
		}
	}

	@PostMapping("/login_fail_handler")
	public String processLogin(nguoiDung nd, ModelMap model) {
		model.addAttribute("ERROR1", "Sai mật khẩu!");
		return "loginRegister";
	}

	@PostMapping("/login_success_handler")
	public String successLoginHandler(nguoiDung nd) {

		return "redirect:/";
	}

	
	
	
	@RequestMapping("/tim-kiem/{pageNo}")
	public String timKiemQA(Model model, @Param("keyword") String keyword, @PathVariable(value = "pageNo") int pageNo, @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		// Cập nhập giỏ hàng
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			List<gioHangSession> cartItems = (ArrayList<gioHangSession>) session.getAttribute("cartItems");
			if (cartItems == null) {
				cartItems = new ArrayList<gioHangSession>();
			}
			model.addAttribute("cartItems", cartItems);
			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
			List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
			model.addAttribute("listLQAInNav", listLQAInNav);
			model.addAttribute("listHQAInNav", listHQAInNav);
		} else {
			nguoiDung nguoiDungODAY = repo.findByEmail(test);
			List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
			model.addAttribute("cartItems", cartItems);
			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
			List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
			model.addAttribute("listLQAInNav", listLQAInNav);
			model.addAttribute("listHQAInNav", listHQAInNav);
		}
		
		int pageSize = 8;
		if(sortField == null && sortDir == null) {
			sortField = "giaTien";
			sortDir = "asc";
		}
		Page<quanAo> page = qaService.timKiemTQA(keyword, pageNo, pageSize, sortField, sortDir);
		List<quanAo> listQA = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listQA", listQA);
		model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    System.out.println(sortDir + "    " + sortField + "    " + keyword);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		return "timKiem";
	}

}
