package com.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.form.LoginForm;
import com.web.model.Point;
import com.web.model.User;
import com.web.repository.PointRepository;

@RequestMapping("/userdetail")
@Controller
public class UserDetailController {

	@Autowired
	HttpSession session;

	@Autowired
	private PointRepository pointRepository;

	@GetMapping
	public String input(@ModelAttribute LoginForm form,Model model) {
		// セッションを取得
		User user = (User) session.getAttribute("loginUser");

		//ポイント情報を取得
		Point userPoint = pointRepository.findByUserId(user.getId());
		//購入履歴情報を取得



		model.addAttribute("user", user);
		model.addAttribute("point", userPoint.getPoint());

		return "user/userdetail";

	}
}
