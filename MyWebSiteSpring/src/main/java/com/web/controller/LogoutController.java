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
public class LogoutController {

	@Autowired
	HttpSession session;

	@GetMapping
	public String logout(@ModelAttribute LoginForm form,Model model,RedirectAttributes attribute) {

		//セッションを削除して、ログイン画面にも戻る
		session.invalidate();
		attribute.addFlashAttribute("logoutMessage", "ログアウトしました");
		return "redirect:/login";
	}

}
