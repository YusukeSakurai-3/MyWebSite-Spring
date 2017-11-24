package com.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.BuyConfirmForm;
import com.web.model.Buy;
import com.web.model.DeliveryMethod;
import com.web.model.Item;
import com.web.model.Point;
import com.web.model.User;
import com.web.repository.DeliveryMethodRepository;
import com.web.repository.PointRepository;
import com.web.util.Util;

@Controller
public class BuyConfirmController {

	@Autowired
	private DeliveryMethodRepository deliveryMethodRepository;

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/buyconfirm", method = RequestMethod.POST)
	public String buyConfirm(@Validated  @ModelAttribute BuyConfirmForm form, BindingResult result,Model model,RedirectAttributes attribute) {

		if (result.hasErrors()) {
			attribute.addFlashAttribute("buyErrorMessage", "不正な入力がされています");
			return "redirect:/buy";
		}

		// セッションを取得
		User user = (User) session.getAttribute("loginUser");
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
		//ポイント情報取得配送方法取得
		Point userPoint = pointRepository.findByUserId(user.getId());


		//選択された配送方法を取得
		DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(form.getDeliveryMethodId());
		int point = form.getPoint()!=null?form.getPoint():0;


		//商品の合計金額を取得
		//ポイントを引かない状態の合計金額
		int preTotalPrice = Util.getTotalItemPrice(cart)+deliveryMethod.getPrice();

		if(point > preTotalPrice) {
			attribute.addFlashAttribute("buyErrorMessage", "ポイントが合計金額を上回っています");
			return "redirect:/buy";
		}else if(point > userPoint.getPoint()) {
			attribute.addFlashAttribute("buyErrorMessage", "ポイントが所持ポイントを上回っています");
			return "redirect:/buy";
		}

		int totalPrice = preTotalPrice - point;

		//購入完了時使用(point,buyData)
		Buy buyData = new Buy(user.getId(),totalPrice,deliveryMethod.getId(),point);
		Point pointData = new Point(userPoint,user.getId(),point,preTotalPrice);
		session.setAttribute("buyData",buyData);
		session.setAttribute("pointData", pointData);


		session.setAttribute("deliveryMethod",deliveryMethod);

		model.addAttribute("usingPoint", point);
		model.addAttribute("totalPrice", totalPrice);


		return "buy/buyconfirm";


	}

}
