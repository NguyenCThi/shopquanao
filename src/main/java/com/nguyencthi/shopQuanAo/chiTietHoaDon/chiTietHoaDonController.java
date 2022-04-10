package com.nguyencthi.shopQuanAo.chiTietHoaDon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nguyencthi.shopQuanAo.gioHang.gioHang;
import com.nguyencthi.shopQuanAo.gioHang.gioHangRepository;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAo;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAoRepository;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAo;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAoRepository;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDungRepository;

@Controller
public class chiTietHoaDonController {
	@Autowired
	private chiTietHoaDonRepository cthdRepo;
	
	@Autowired
	private nguoiDungRepository repo;
	@Autowired
	private gioHangRepository cartRepo;
	@Autowired
    private hangQuanAoRepository hqaRepo; 
    @Autowired 
    private loaiQuanAoRepository lqaRepo;
	
	@GetMapping("/hoa-don-nd/chi-tiet/{idHoaDon}")
	public String showCTHD(@PathVariable("idHoaDon") Integer idHoaDon, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String test = auth.getName();
		if (test == "anonymousUser") {
			return "redirect:/";
		}
		nguoiDung nguoiDungODAY = repo.findByEmail(test);
		List<gioHang> cartItems = cartRepo.findByNguoiDung(nguoiDungODAY.getIdNguoiDung());
		List<chiTietHoaDon> listHD = cthdRepo.findByIdHD(idHoaDon);
		List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
        List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
        model.addAttribute("listLQAInNav", listLQAInNav);
        model.addAttribute("listHQAInNav", listHQAInNav);
		model.addAttribute("listHD", listHD);
		model.addAttribute("cartItems", cartItems);
		return "chiTietHoaDon";
	}
	
}
