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
import com.web.util.Util;

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

		//商品を取得
		Page<Item> randomPage = itemRepository.findRand(new PageRequest(0, MAX_ITEM));
		List<Item> randomList = randomPage.getContent();

		//itemIdと対応するimgのHashMap
		HashMap<Integer,String> itemImg = Util.itemImg(randomList);

		//modelにセットする
		model.addAttribute("index", "index");
		model.addAttribute("itemImg", itemImg);

		model.addAttribute("itemList", randomList);

	    return "index/index";


	}


}
