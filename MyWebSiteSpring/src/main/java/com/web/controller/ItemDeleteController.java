package com.web.controller;

import java.util.ArrayList;

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
public class ItemDeleteController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemdelete", method = RequestMethod.POST)
	public String item(@RequestParam(value="deleteIdList", required = false) String[] deleteIdList,Model model,RedirectAttributes attribute){

		//カートを取得
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		String cartActionMessage = "";

		if( deleteIdList != null) {
			//削除対象の商品を削除
			for (String deleteItemId : deleteIdList) {
				for (Item cartInItem : cart) {
					if (cartInItem.getId() == Integer.parseInt(deleteItemId)) {
						cart.remove(cartInItem);
						break;
					}
				}
			}
			cartActionMessage = "削除しました";
		} else {
			cartActionMessage = "削除する商品が選択されていません";
		}


		//カート情報更新
		session.setAttribute("cart", cart);
		attribute.addFlashAttribute("cartActionMessage", cartActionMessage);

	    return "redirect:/cart";


	}

}
