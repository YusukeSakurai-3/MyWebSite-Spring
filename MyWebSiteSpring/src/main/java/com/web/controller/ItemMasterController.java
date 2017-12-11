package com.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.model.Item;
import com.web.repository.BuyDetailRepository;
import com.web.repository.ItemRepository;
import com.web.util.Util;

@Controller
public class ItemMasterController {
	//1ページに表示する商品数
	final static int MAX_ITEM = 20;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	BuyDetailRepository buyDetailRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemmaster", method = RequestMethod.GET)
	public String itemmaster(@RequestParam(name = "pageNum",defaultValue = "0") String pageN,Model model){

		int pageNum = Integer.parseInt(pageN);


		//商品を取得
		//HashMap<Integer, Long> purchaseNum = new HashMap<Integer, Long>();
		Page<Item> itemPage = (Page<Item>)itemRepository.findAll(new PageRequest(pageNum, MAX_ITEM));
		List<Item> itemList = itemPage.getContent();
		//商品総数
		long totalElements = itemPage.getTotalElements();

		//ページ総数
		int maxPage = itemPage.getTotalPages();
		System.out.println(maxPage);

		for(Item item: itemList) {
			//title縮小
			item.setName(Util.getsubString(item.getName(), 30));
		}


		List<Item> itemData = (List<Item>)itemRepository.findAll();
		//test
		System.out.println("商品詳細取得");
		System.out.println(itemData.get(0).getBuyDetailList().size());
		for(Item item: itemData) {
			System.out.println(item.getName()+" 購入数:"+item.getPurchaseNumber());
		}

		System.out.println(totalElements);

		model.addAttribute("totalElements", totalElements);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageMax",maxPage);

	    return "item/itemmaster";

	}


}
