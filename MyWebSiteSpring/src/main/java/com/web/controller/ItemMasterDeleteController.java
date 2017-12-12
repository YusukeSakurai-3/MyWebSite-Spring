package com.web.controller;

import java.util.HashMap;
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
import com.web.util.Util;

@Controller
public class ItemMasterDeleteController {

	@Autowired
	ItemRepository itemRepository;


	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemmasterdelete", method = RequestMethod.GET)
	public String itemmaster(@RequestParam("itemId") String itemId,Model model){


		List<Item> itemList = (List<Item>)itemRepository.findById(Integer.parseInt(itemId));
		Item item = itemList.get(0);

		HashMap<Integer, String> itemImg = Util.itemImg(itemList);


		model.addAttribute("item", item);
		model.addAttribute("itemImg", itemImg);

	    return "item/itemmasterdelete";

	}

	@RequestMapping(value = "/itemmasterdelete", method = RequestMethod.POST)
	public String itemmasterdelete(@RequestParam("itemId") String itemId,Model model,RedirectAttributes attribute ){


		List<Item> itemList = (List<Item>)itemRepository.findById(Integer.parseInt(itemId));
		Item item = itemList.get(0);

		itemRepository.delete(item);

		attribute.addFlashAttribute("itemActionMessage", "商品を削除しました");


	    return "redirect:itemmaster";

	}

}
