package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.model.ItemGetList;
import com.web.model.Review;
import com.web.model.User;
import com.web.repository.ItemGetListRepository;
import com.web.repository.PointRepository;
import com.web.repository.ReviewRepository;
import com.web.repository.UserRepository;

@RequestMapping("/userDeleteMaster")
@Controller
public class UserDeleteMaster {


	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemGetListRepository itemGetListRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	PointRepository pointRepository;

	@GetMapping
	public String user(@RequestParam("userId") String userId,Model model) {

		//ユーザー情報を取得
		User user = userRepository.findById(Integer.parseInt(userId));


		model.addAttribute("user",user);
		return "user/userdeletemaster";
     }


	@PostMapping
	public String userdelete(@RequestParam("userId") String userId,Model model,RedirectAttributes attribute )  {

		//ユーザー情報を取得
		User user = userRepository.findById(Integer.parseInt(userId));
		System.out.println("userId:"+user.getPoint().get(0).getUserId());

		//関連する情報を削除
		List<Review> userReview = reviewRepository.findByUserId(Integer.parseInt(userId));
		List<ItemGetList> itemList = itemGetListRepository.findByUserId(Integer.parseInt(userId));


		itemGetListRepository.delete(itemList);
		reviewRepository.delete(userReview);
		userRepository.delete(user);

		//modelにセットする
		attribute.addFlashAttribute("userActionMessage","ユーザーを削除しました");

	    return "redirect:userlist";


	}
}
