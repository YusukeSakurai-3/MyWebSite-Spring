package com.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.model.Item;
import com.web.repository.ItemRepository;

@Controller
public class ItemAddController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemadd", method = RequestMethod.POST)
	public String item(@RequestParam("itemId") String itemId,Model model,RedirectAttributes attribute){
		System.out.println(itemId);


		List<Item> itemList = (List<Item>)itemRepository.findById(Integer.parseInt(itemId));

		Item item = itemList.get(0);
		System.out.println(item.getName());

		//カートを取得
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");


		//セッションにカートがない場合カートを作成
		if(cart==null) {
			cart = new ArrayList<Item>();
		}

		cart.add(item);

		//カート情報更新
		session.setAttribute("cart", cart);
		attribute.addFlashAttribute("cartActionMessage", "商品を追加しました");

	    return "redirect:/cart";


	}

}
