package com.web.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.form.UserSearchForm;
import com.web.model.User;
import com.web.repository.UserRepository;
import com.web.util.UserCheck;

@Controller
public class UserListController {

	//1ページに表示する商品数
	final static int PAGE_MAX_USER_COUNT = 20;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/userlist",method = RequestMethod.GET)
	public String userlist(@Validated UserSearchForm form,Model model) throws ParseException {

	    //ユーザーを取得
	    Page<User> userPage = userRepository.findAll(new PageRequest(0,PAGE_MAX_USER_COUNT));


		List<User> userList = userPage.getContent();

		//modelにセットする
		model.addAttribute("userList", userList);

	    return "user/userlist";


	}


	@RequestMapping(value = "/userlistSearch",method = RequestMethod.GET)
	public String userlistSearch(@Validated UserSearchForm form,Model model) throws ParseException {
		UserCheck check = new UserCheck(form);

	    //ユーザーを取得
	    Page<User> userPage = userRepository.findByNameContains(form.getSearchName(),new PageRequest(0,PAGE_MAX_USER_COUNT));


	    //入力に応じて検索する
	    if(check.getStartDateCheck()&&check.getEndDateCheck()) {
 	       userPage =
	    		     userRepository.findByNameContainsAndBirthDateAfterAndBirthDateBefore(form.getSearchName(),
	    		    		 form.getFormatStartDate(),form.getFormatEndDate(),new PageRequest(0,PAGE_MAX_USER_COUNT));

	    }else if(check.getStartDateCheck()&&!check.getEndDateCheck()) {
    	       userPage =
    	    		     userRepository.findByNameContainsAndBirthDateAfter(form.getSearchName(),form.getFormatStartDate(),new PageRequest(0,PAGE_MAX_USER_COUNT));

	    } else if(!check.getStartDateCheck()&&check.getEndDateCheck()) {
	    	    userPage =
	    	    		userRepository.findByNameContainsAndBirthDateBefore(form.getSearchName(),form.getFormatEndDate(),new PageRequest(0,PAGE_MAX_USER_COUNT));
	    }

		List<User> userList = userPage.getContent();

		if(check.getIdCheck()) {
	        userList = userRepository.findByLoginId(form.getLoginId());
        }

		//modelにセットする
		model.addAttribute("form", form);
		model.addAttribute("userList", userList);

	    return "user/userlist";


	}

}
