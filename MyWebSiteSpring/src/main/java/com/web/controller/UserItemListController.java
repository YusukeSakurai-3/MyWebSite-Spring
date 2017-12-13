package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.model.User;
import com.web.repository.UserRepository;

@Controller
public class UserItemListController {

	//1ページに表示する商品数
		final static int PAGE_MAX_USER_COUNT = 20;

		@Autowired
		UserRepository userRepository;

		@Autowired
		HttpSession session;

		@RequestMapping(value = "/userItemList",method = RequestMethod.GET)
		public String useritemlist(Model model)  {

		    //ユーザーを取得
		    Page<User> userPage = userRepository.findByIsOpenTrue(new PageRequest(0,PAGE_MAX_USER_COUNT));


			List<User> userList = userPage.getContent();

			//modelにセットする
			model.addAttribute("userList", userList);

		    return "user/useritemlist";


		}

}
