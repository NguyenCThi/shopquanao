package com.nguyencthi.shopQuanAo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.nguyencthi.shopQuanAo.gioHang.gioHang;
import com.nguyencthi.shopQuanAo.gioHang.gioHangRepository;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAo;
import com.nguyencthi.shopQuanAo.hangQuanAo.hangQuanAoRepository;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDung;
import com.nguyencthi.shopQuanAo.nguoiDung.nguoiDungRepository;
import com.nguyencthi.shopQuanAo.quanAo.quanAo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class nguoiDungRepositoryTests {

	@Autowired
	private nguoiDungRepository repo;

	@Autowired
	private hangQuanAoRepository hqaRepo;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUser() {
		nguoiDung nd = new nguoiDung();
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String datehere = date.format(formatter);
		nd.setEmail("cooledm123@gmail.com");
		nd.setPassword("onepiece123");
		nd.setHoTen("Nguyễn Chính Thi");
		nd.setSoDienThoai("0935949965");
		nd.setActive(true);
		nd.setNgayTao(datehere);
		nd.setRoles(1);
		nguoiDung saveND = repo.save(nd);
		nguoiDung existND = entityManager.find(nguoiDung.class, saveND.getIdNguoiDung());
		assertThat(existND.getEmail()).isEqualTo(nd.getEmail());
	}

	@Autowired
	private gioHangRepository cartRepo;
	
	@Test
	public void testFindUserByEmail() {
		String email ="cooledm123@gmail.com";
		nguoiDung nd = repo.findByEmail(email);
		assertThat(nd).isNotNull();
	}
	
	@Test
	public void testCreateCategory() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String datehere = date.format(formatter);
		hangQuanAo savedHang = hqaRepo.save(new hangQuanAo("Adidas AG", "Adidas", datehere, datehere, datehere, ""));
		assertThat(savedHang.getIdHangQA()).isGreaterThan(0);
	}

	@Test
	public void addOneCartItem() {
		nguoiDung nd = entityManager.find(nguoiDung.class, 1);
		quanAo qa = entityManager.find(quanAo.class, 4);
		gioHang newItem = new gioHang();
		newItem.setNguoiDung(nd);
		newItem.setQuanAo(qa);
		newItem.setSoLuong(3);
		
		gioHang saveCartItem = cartRepo.save(newItem);
		assertTrue(saveCartItem.getIdGioHang() > 0);
	}
	
	
	
}
