package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.LoginForm;
import com.web.model.User;
import com.web.repository.UserRepository;
import com.web.util.Util;


@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	HttpSession session;

	@GetMapping
	public String input(@ModelAttribute LoginForm form,Model model) {
		// セッションを取得
		User user = (User) session.getAttribute("loginUser");

		if(user!=null){
			model.addAttribute("loginUser", user);
		    return "redirect:/index";

		}else {
		    return "login/input";

		}
	}

	@PostMapping
	public String result(@Validated @ModelAttribute LoginForm form, BindingResult result, Model model,RedirectAttributes attribute ) {
		if (result.hasErrors()) {
			return input(form,model);
		}
		System.out.println(form.getLoginId());
		System.out.println(form.getPassword());

		List<User> user = (List<User>) userRepository.findByLoginIdAndPassword(form.getLoginId(),Util.toCode(form.getPassword()));
		if(user.size()==0) {
			attribute.addFlashAttribute("msg", "loginIdかpasswordに誤りがあります");
			return "redirect:/login";
		}else {

			// セッションへ保存
			session.setAttribute("login",true);
			session.setAttribute("loginUser",user.get(0));

			attribute.addFlashAttribute("msg", "ようこそ"+user.get(0).getName()+"さん!");

		    return "redirect:/index";
	    }

	}
}
