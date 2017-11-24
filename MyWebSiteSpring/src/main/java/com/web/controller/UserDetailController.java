package com.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.Buy;
import com.web.model.DeliveryMethod;
import com.web.model.Point;
import com.web.model.User;
import com.web.repository.BuyRepository;
import com.web.repository.DeliveryMethodRepository;
import com.web.repository.PointRepository;

@RequestMapping("/userdetail")
@Controller
public class UserDetailController {

	@Autowired
	HttpSession session;

	@Autowired
	private PointRepository pointRepository;
	@Autowired
	private DeliveryMethodRepository deliveryMethodRepository;
	@Autowired
	private BuyRepository buyRepository;


	@GetMapping
	public String userDetail(Model model) {
		// セッションを取得
		User user = (User) session.getAttribute("loginUser");

		//ポイント情報を取得
		Point userPoint = pointRepository.findByUserId(user.getId());
		//購入履歴情報を取得
		List<Buy> buyData = buyRepository.findByUserId(user.getId());

		//Idにひもづいた配送方法のリスト
		List<DeliveryMethod> deliveryMethodList = (List<DeliveryMethod>)deliveryMethodRepository.findAll();
		HashMap<Integer,String> deliveryMethodMap = DeliveryMethod.getDeliveryMethodName(deliveryMethodList);

		model.addAttribute("user", user);
		model.addAttribute("point", userPoint.getPoint());
		model.addAttribute("buyDataList", buyData);
		model.addAttribute("deliveryMethodMap", deliveryMethodMap);

		return "user/userdetail";

	}
}
