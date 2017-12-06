package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.Buy;
import com.web.model.BuyDetail;
import com.web.model.DeliveryMethod;
import com.web.model.Item;
import com.web.model.Review;
import com.web.model.User;
import com.web.repository.BuyDetailRepository;
import com.web.repository.BuyRepository;
import com.web.repository.DeliveryMethodRepository;
import com.web.repository.ItemRepository;
import com.web.repository.ReviewRepository;

//@RequestMapping("/userbuyhistorydetail")
@Controller
public class UserBuyHistoryDetailController {
	@Autowired
	HttpSession session;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BuyDetailRepository buyDetailRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private DeliveryMethodRepository deliveryMethodRepository;

	@Autowired
	private BuyRepository buyRepository;

	@RequestMapping(value = "/userbuyhistorydetail")
	public String userbuyhistorydetail(@RequestParam("buyId") String buyId,Model model){
		System.out.println(buyId);
		// セッションを取得
     	User user = (User) session.getAttribute("loginUser");

		//選択されたIdをもとに購入情報を取得する
		List<Buy> buyDataList = (List<Buy>) buyRepository.findById(Integer.parseInt(buyId));
		//選択されたIdをもとに商品情報を取得する
		List<BuyDetail> buyDetailList = (List<BuyDetail>) buyDetailRepository.findByBuyId(Integer.parseInt(buyId));
		//レビューされているか
		HashMap<Integer, Boolean> isReviewed = new HashMap<Integer, Boolean>();

		ArrayList<Item> itemList = new ArrayList<Item>();
		for(BuyDetail detail: buyDetailList) {
			System.out.println(detail.getItemId());
			List<Item> item = (List<Item>) itemRepository.findById(detail.getItemId());
			itemList.add(item.get(0));
			//レビューされているかチェック
			List<Review> review = (List<Review>)reviewRepository.findByUserIdAndItemId(user.getId(),detail.getItemId());
			if(review.size()==0) {
				isReviewed.put(detail.getItemId(),false);
			}else {
				isReviewed.put(detail.getItemId(),true);
			}

		}


		List<DeliveryMethod> deliveryMethodList = (List<DeliveryMethod>) deliveryMethodRepository.findAll();
		HashMap<Integer, String> deliveryName = DeliveryMethod.getDeliveryMethodName(deliveryMethodList);
		HashMap<Integer, Integer> deliveryPrice = DeliveryMethod.getDeliveryMethodPrice(deliveryMethodList);


		model.addAttribute("itemList",itemList);
		model.addAttribute("deliveryPrice",deliveryPrice);
		model.addAttribute("deliveryName",deliveryName);
		model.addAttribute("buyData",buyDataList.get(0));
		model.addAttribute("isReviewed",isReviewed);

		return "/user/userbuyhistorydetail";
	}

}
