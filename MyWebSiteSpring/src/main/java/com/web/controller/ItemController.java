package com.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.Item;
import com.web.model.Review;
import com.web.model.User;
import com.web.repository.ItemRepository;
import com.web.repository.ReviewRepository;
import com.web.repository.UserRepository;
import com.web.util.Util;


@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
    UserRepository userRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public String item(@RequestParam("itemId") String itemId,Model model){

		List<Item> itemList = (List<Item>)itemRepository.findById(Integer.parseInt(itemId));
		Item item = itemList.get(0);

		List<Review> reviewList = reviewRepository.findByItemId(Integer.parseInt(itemId));


		//レビューしたユーザーの情報を得
		HashMap<Integer, String> reviewUserName = new HashMap<Integer, String>();
		for(Review review: reviewList) {
		    User reviewUser = userRepository.findById(review.getUserId());
		    reviewUserName.put(review.getId(),reviewUser.getName());
		}

		HashMap<Integer, String> reviewImg = Util.reviewImg(reviewList);

		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemList);

		model.addAttribute("reviewList", reviewList);
		model.addAttribute("reviewUserName", reviewUserName);
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("reviewImg", reviewImg);
		model.addAttribute("item", item);

	    return "item/item";


	}

}
