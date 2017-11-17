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

import com.web.form.UserCreateForm;
import com.web.model.Point;
import com.web.model.User;
import com.web.repository.PointRepository;
import com.web.repository.UserRepository;

@RequestMapping("/usercreate")
@Controller
public class UserCreateController {
	@Autowired
	HttpSession session;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PointRepository pointRepository;

	@GetMapping
	public String input(@ModelAttribute UserCreateForm form,Model model) {
		    return "user/usercreate";
     }

	@PostMapping
	public String result(@Validated  @ModelAttribute UserCreateForm form, BindingResult result, Model model,RedirectAttributes attribute ) {

		if (result.hasErrors()) {
			return input(form,model);
		}

		List<User> sameIdUser = (List<User>) userRepository.findByLoginId(form.getLoginId());
		if(sameIdUser.size()!=0) {
			model.addAttribute("msg", "loginIdが重複しています。");
			return input(form,model);
		}else if (!form.getPassword().equals(form.getPasswordConfirm())){
			model.addAttribute("passErrorMsg", "パスワードとパスワード(確認)が一致していません。");
			return input(form,model);
		}else {
			//ユーザー登録する
			User newUser = new User(form);
			userRepository.save(newUser);

			//point登録する
			Point newUserPoint = new Point(newUser.getId());
			pointRepository.save(newUserPoint);

			// セッションへ保存
			session.setAttribute("login",true);
			session.setAttribute("loginUser",newUser);

			attribute.addFlashAttribute("newUserMsg", "新規登録しました。ようこそ"+newUser.getName()+"さん!");

		    return "redirect:/index";
	    }

	}



}
