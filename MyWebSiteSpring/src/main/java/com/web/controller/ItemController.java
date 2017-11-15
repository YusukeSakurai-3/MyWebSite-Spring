package com.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.Item;
import com.web.repository.ItemRepository;

@RequestMapping("/item")
@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	HttpSession session;

	@GetMapping
	public String item(Model model) {

		List<Item> itemList = (List<Item>)itemRepository.findById(3);
		Item item = itemList.get(0);


		HashMap<Integer, String> itemImg = new HashMap<Integer, String>();

		String img = "img/"+item.getFileName();
		itemImg.put(item.getId(),img );

		model.addAttribute("itemImg", itemImg);
		model.addAttribute("item", item);

	    return "item/item";


	}

}
