package com.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.Item;
import com.web.repository.ItemRepository;

@RequestMapping("/index")
@Controller

public class Index {
	//1ページに表示する商品数
	final static int MAX_ITEM = 4;
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	HttpSession session;

	@GetMapping
	public String index(Model model) {
		String index = "index";

		//商品を取得
		Page<Item> randomPage = itemRepository.findRand(new PageRequest(0, MAX_ITEM));
		List<Item> randomList = randomPage.getContent();






		HashMap<Integer,String> itemImg = new HashMap<Integer,String>();
		HashMap<Integer,String> itemUrl = new HashMap<Integer,String>();
		for(Item item: randomList) {
		    String img = "img/"+item.getFileName();
		    String url = "Item?item_id="+item.getId();

		    itemUrl.put(item.getId(),url);
		    itemImg.put(item.getId(),img);
		}


		model.addAttribute("index", index);
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("itemUrl", itemUrl);
		model.addAttribute("itemList", randomList);



	    return "index/index";


	}


}
