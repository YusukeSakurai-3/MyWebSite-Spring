package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.User;
import com.web.repository.UserRepository;


@Controller
public class UserDetailMasterController {


	@Autowired
	private UserRepository userRepository;


	@RequestMapping("/userDetailMaster")
	public String userDetail(@RequestParam("userId") String userId,Model model) {


		//ユーザー情報を取得
		User user = userRepository.findById(Integer.parseInt(userId));

		model.addAttribute("user", user);

		return "user/userdetailmaster";

	}

}
