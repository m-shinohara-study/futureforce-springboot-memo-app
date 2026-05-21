package com.lesson.memo.controller;

import java.time.LocalDateTime;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 新規登録画面の表示
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin/signup";
	}
	
	@PostMapping("/signup")
	public String register(
			@ModelAttribute
			@Valid
			Admin admin,
			BindingResult result) {
	    // 入力チェック
	    if (result.hasErrors()) {
	        return "admin/signup";
	    }
	    
	    // 画面から入力されたパスワードを取得し、暗号化してからセットし直す
	    String encodedPassword = passwordEncoder.encode(admin.getPassword());
	    admin.setPassword(encodedPassword);
	    
	    // データの保存後、リダイレクト
	    admin.setCreatedAt(LocalDateTime.now());
	    admin.setUpdatedAt(LocalDateTime.now());
	    adminRepository.save(admin);
	    return "redirect:/admin/signin";
	}
	
	// ログイン画面の表示
	@GetMapping("/signin")
	public String signin() {
		return "admin/signin";
	}
}