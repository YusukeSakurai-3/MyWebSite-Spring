package com.web.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.Item;
import com.web.repository.ItemRepository;
import com.web.util.Util;


@Controller
public class RandomItemController {
	//ランダムに商品を1つ取得する
	final static int MAX_ITEM = 1;

	@Autowired
	ItemRepository itemRepository;

	@RequestMapping("/random")
	public String random(Model model){

		//商品を取得
		Page<Item> randomPage = itemRepository.findRand(new PageRequest(0, MAX_ITEM));
		List<Item> itemList = randomPage.getContent();


		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemList);

		Item item = itemList.get(0);

		model.addAttribute("itemImg", itemImg);
		model.addAttribute("item", item);

	    return "item/item";


	}


}
