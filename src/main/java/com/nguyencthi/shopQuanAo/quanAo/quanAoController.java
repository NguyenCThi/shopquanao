package com.nguyencthi.shopQuanAo.quanAo;

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
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAo;
import com.nguyencthi.shopQuanAo.loaiQuanAo.loaiQuanAoRepository;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDungRepository;

@Controller
public class quanAoController {
@Autowired
	private quanAoRepository repoQA;
	@Autowired
	private HttpSession session;
	@Autowired
	private nguoiDungRepository repoND;
	@Autowired
	private gioHangRepository cartRepo;
	@Autowired
    private hangQuanAoRepository hqaRepo; 
    @Autowired 
    private loaiQuanAoRepository lqaRepo;
    @Autowired 
    private quanAoServiceImpl qaService;
    
//    -----
@GetMapping("/products")
public String listCategories(Model model) {
	return showAll(1, model, "giaTien", "asc");
}

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
    			model.addAttribute("cartItems", cartItems);
    			List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
                List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
                model.addAttribute("listLQAInNav", listLQAInNav);
                model.addAttribute("listHQAInNav", listHQAInNav);
    		}

    		quanAo product = repoQA.findById(idQuanAo).get();
    		List<quanAo> listQA = repoQA.get3RandomItem();
    		model.addAttribute("product", product);
    		model.addAttribute("listQA", listQA);
    		return "detailsQA";
    	}
}
@GetMapping("/products/{pageNo}")
public String showAll(@PathVariable(value = "pageNo") int pageNo, Model model, @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
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
	model.addAttribute("cartItems", cartItems);
	List<hangQuanAo> listHQAInNav = hqaRepo.indexShow();
	List<loaiQuanAo> listLQAInNav = lqaRepo.indexShow1();
	model.addAttribute("listLQAInNav", listLQAInNav);
	model.addAttribute("listHQAInNav", listHQAInNav);
}
int pageSize = 8;
Page<quanAo> page = qaService.findAllPage(pageNo, pageSize, sortField, sortDir);
List<quanAo> listQA = page.getContent();
model.addAttribute("currentPage", pageNo);
model.addAttribute("totalPages", page.getTotalPages());
model.addAttribute("totalItems", page.getTotalElements());
model.addAttribute("sortField", sortField);
model.addAttribute("sortDir", sortDir);
model.addAttribute("listQA", listQA);
model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
return "qa";
}
}
