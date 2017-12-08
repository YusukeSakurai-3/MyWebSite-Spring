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

import com.web.model.Item;
import com.web.repository.BuyDetailRepository;
import com.web.repository.ItemRepository;
import com.web.util.Util;

@Controller
public class ItemMaster {
	//1ページに表示する商品数
	final static int MAX_ITEM = 20;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	BuyDetailRepository buyDetailRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemmaster", method = RequestMethod.GET)
	public String itemmaster(Model model){


		//商品を取得
		//HashMap<Integer, Long> purchaseNum = new HashMap<Integer, Long>();
		Page<Item> itemPage = (Page<Item>)itemRepository.findAll(new PageRequest(0, MAX_ITEM));
		List<Item> itemList = itemPage.getContent();
		for(Item item: itemList) {
			//title縮小
			item.setName(Util.getsubString(item.getName(), 30));
//			購入数を取得する
//			List<Long> purchaseN = (List<Long>) buyDetailRepository.findCount(item.getId());
//			if(purchaseN.size()!=0) {
//				purchaseNum.put(item.getId(), (int)purchaseN.get(0).getPurchaseNumber());
//				purchaseNum.put(item.getId(), purchaseN.get(0));
//			}else {
//				purchaseNum.put(item.getId(), 0L);
//			}
		}


		List<Item> itemData = (List<Item>)itemRepository.findAll();
		//test
		System.out.println("商品詳細取得");
		System.out.println(itemData.get(0).getBuyDetailList().size());
		for(Item item: itemData) {
			System.out.println(item.getName()+" 購入数:"+item.getBuyDetailList().size());
		}


		model.addAttribute("itemList", itemList);



	    return "item/itemmaster";

	}


}
