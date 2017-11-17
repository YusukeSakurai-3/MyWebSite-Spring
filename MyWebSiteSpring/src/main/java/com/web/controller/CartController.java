package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.form.LoginForm;
import com.web.model.Item;
import com.web.util.Util;

@RequestMapping("/cart")
@Controller
public class CartController {

	@Autowired
	HttpSession session;

	@GetMapping
	public String cart(@ModelAttribute LoginForm form,Model model) {
	ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");


	//セッションにカートがない場合カートを作成
		if (cart == null) {
			cart = new ArrayList<Item>();
			session.setAttribute("cart", cart);
		}

		String cartActionMessage = "";
		//カートに商品が入っていないなら
		if(cart.size() == 0) {
			cartActionMessage = "カートに商品がありません";
		}

		HashMap<Integer,String> itemImg = Util.itemImg(cart);
		model.addAttribute("itemImg",itemImg);

		return "cart/cart";

	}
}

