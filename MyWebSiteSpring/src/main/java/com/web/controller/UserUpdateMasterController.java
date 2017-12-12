package com.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.UserUpdateMasterForm;
import com.web.model.User;
import com.web.repository.UserRepository;

@RequestMapping("/userUpdateMaster")
@Controller
public class UserUpdateMasterController {
	@Autowired
	HttpSession session;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String input(@RequestParam("userId") String userId,@ModelAttribute UserUpdateMasterForm form,Model model) {


		//ユーザー情報を取得
		User user = userRepository.findById(Integer.parseInt(userId));
		model.addAttribute("user",user);
		return "user/userupdatemaster";
     }


	@PostMapping
	public String result(@Validated  @ModelAttribute UserUpdateMasterForm form, BindingResult result,Model model,RedirectAttributes attribute ) {
		if (result.hasErrors()) {
			return input(form.getUserId(),form,model);
		}

		if (!form.getPassword().equals(form.getPasswordConfirm())){
			model.addAttribute("passErrorMsg", "パスワードとパスワード(確認)が一致していません。");
			return input(form.getUserId(),form,model);
		}else {

			//ユーザー更新する
			User updateUser = new User(form);
			userRepository.save(updateUser);


			attribute.addFlashAttribute("userActionMessage", "ユーザ情報を更新しました。");

		    return "redirect:index";
	    }

	}

}
