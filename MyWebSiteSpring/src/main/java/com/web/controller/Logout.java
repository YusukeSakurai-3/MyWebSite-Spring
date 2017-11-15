package com.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.LoginForm;

@RequestMapping("/logout")
@Controller
public class Logout {

	@Autowired
	HttpSession session;

	@GetMapping
	public String logout(@ModelAttribute LoginForm form,Model model,RedirectAttributes attribute) {

		//セッション削除
		session.invalidate();
		attribute.addFlashAttribute("logoutMessage", "ログアウトしました");
		return "redirect:/login";
	}

}
