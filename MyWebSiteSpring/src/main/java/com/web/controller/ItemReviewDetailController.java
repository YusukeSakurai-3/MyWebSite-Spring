package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.model.Review;
import com.web.model.User;
import com.web.repository.ItemRepository;
import com.web.repository.ReviewRepository;

@Controller
public class ItemReviewDetailController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemreviewdetail", method = RequestMethod.GET)
	public String itemreviewdetail(@RequestParam("reviewId") String reviewId,Model model){

		List<Review> reviewList = (List<Review>)reviewRepository.findById(Integer.parseInt(reviewId));
		Review review = reviewList.get(0);


		//itemIdと対応するimgのHashMap
		String reviewImg = "/img/"+review.getFileName();

		model.addAttribute("reviewImg", reviewImg);
		model.addAttribute("review", review);

	    return "item/itemreviewdetail";

	}

	@RequestMapping(value = "/itemreviewdetail", method = RequestMethod.POST)
	public String reviewdelete(@RequestParam("deleteId") String deleteId,Model model,RedirectAttributes attribute) {


        // セッションを取得
     	User user = (User) session.getAttribute("loginUser");
        //削除するレビューのインスタンス
        List<Review> review = (List<Review>)reviewRepository.findById(Integer.parseInt(deleteId));
        reviewRepository.delete(review.get(0));

        attribute.addFlashAttribute("reviewActionMessage", "レビューを削除しました");


	    return "redirect:itemreviewlist";

        }

}
