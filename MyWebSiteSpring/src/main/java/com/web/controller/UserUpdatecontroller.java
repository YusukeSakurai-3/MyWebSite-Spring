package com.web.controller;

import java.util.ArrayList;

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

import com.web.form.UserUpdateForm;
import com.web.model.Item;
import com.web.model.User;
import com.web.repository.UserRepository;

@RequestMapping("/userupdate")
@Controller
public class UserUpdatecontroller {
	@Autowired
	HttpSession session;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String input(@ModelAttribute UserUpdateForm form,Model model) {
		User user = (User)session.getAttribute("loginUser");
		System.out.println(user.getLoginId());

		model.addAttribute("user",user);
		return "user/userupdate";
     }

	@PostMapping
	public String result(@Validated  @ModelAttribute UserUpdateForm form, BindingResult result,Model model,RedirectAttributes attribute ) {
		if (result.hasErrors()) {
			return input(form,model);
		}

		if (!form.getPassword().equals(form.getPasswordConfirm())){
			model.addAttribute("passErrorMsg", "パスワードとパスワード(確認)が一致していません。");
			return input(form,model);
		}else {
			//カート情報を保持する
			ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");


			//ユーザー更新する
			User updateUser = new User(form);
			userRepository.save(updateUser);

			//セッション削除(ユーザ詳細画面に戻ったとき、情報が更新されているように)
			session.invalidate();
			// セッションへ保存

			session.setAttribute("login",true);
			session.setAttribute("loginUser",updateUser);
			session.setAttribute("cart",cart);

			attribute.addFlashAttribute("updateMessage", "ユーザ情報を更新しました。");

		    return "redirect:/userdetail";
	    }

	}

}
