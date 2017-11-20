package com.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.model.DeliveryMethod;
import com.web.model.Item;
import com.web.model.Point;
import com.web.model.User;
import com.web.repository.DeliveryMethodRepository;
import com.web.repository.PointRepository;

@RequestMapping("/buy")
@Controller
public class BuyController {

	@Autowired
	private DeliveryMethodRepository deliveryMethodRepository;

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	HttpSession session;

	@GetMapping
	public String buy(Model model,RedirectAttributes attribute) {
		// セッションを取得
		User user = (User) session.getAttribute("loginUser");
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");

		if(user==null){
		    return "redirect:/login";
		}else  if(cart.size() == 0){
			attribute.addFlashAttribute("cartActionMessage", "購入する商品がありません");
		    return "redirect:/buy/cart";

		}else {
			//ポイント情報取得
			Point point = pointRepository.findByUserId(user.getId());

			//配送方法取得
			List<DeliveryMethod> deliveryMethodList = (List<DeliveryMethod>)deliveryMethodRepository.findAll();


			model.addAttribute("deliveryMethodList", deliveryMethodList);
			model.addAttribute("cart", cart);
			model.addAttribute("point", point.getPoint());


			return "buy/buy";

		}
	}
}
