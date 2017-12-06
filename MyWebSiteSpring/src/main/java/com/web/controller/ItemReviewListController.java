package com.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.Item;
import com.web.model.Review;
import com.web.model.User;
import com.web.repository.ItemRepository;
import com.web.repository.ReviewRepository;
import com.web.util.Util;

@Controller

public class ItemReviewListController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	HttpSession session;

	@RequestMapping("/itemreviewlist")
	public String itemreviewlist(Model model){

		// セッションを取得
     	User user = (User) session.getAttribute("loginUser");

		List<Review> reviewList = (List<Review>)reviewRepository.findByUserId(user.getId());

		//itemidから商品名を得る
		HashMap<Integer, String> itemName = new HashMap<Integer, String>();
		for(Review review: reviewList) {
			List<Item> item = (List<Item>)itemRepository.findById(review.getItemId());
			itemName.put(item.get(0).getId(), Util.getsubString(item.get(0).getName(), 20));
		}


		//reviewIdと対応するimgのHashMap
		HashMap<Integer, String> reviewImg = Util.reviewImg(reviewList);

		model.addAttribute("reviewImg", reviewImg);
		model.addAttribute("itemName", itemName);
		model.addAttribute("reviewList", reviewList);

	    return "item/itemreviewlist";


	}



}
