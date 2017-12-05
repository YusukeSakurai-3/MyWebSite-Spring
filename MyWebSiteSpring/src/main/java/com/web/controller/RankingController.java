package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.BuyRanking;
import com.web.model.Item;
import com.web.repository.BuyDetailRepository;
import com.web.repository.ItemRepository;
import com.web.util.Util;

@RequestMapping("/ranking")
@Controller
public class RankingController {

	//1ページに表示する商品数
	final static int MAX_ITEM_PAGE = 8;
	@Autowired
	BuyDetailRepository buyDetailRepository;

	@Autowired
	ItemRepository itemRepository;

	@GetMapping
	public String ranking(Model model) {

		//商品の購入数の多いもののIDを取得する
		Page<BuyRanking> buyRankingPage = buyDetailRepository.findRanking(new PageRequest(0, MAX_ITEM_PAGE));
		List<BuyRanking> rankingList = buyRankingPage.getContent();
		System.out.println("itemId:"+rankingList.get(0).getItemId());
		System.out.println("sum:"+rankingList.get(0).getSum());

		//順位に紐付く商品のIdを取得する
		 int ranking[] = new int[MAX_ITEM_PAGE];
		//商品を取得
		ArrayList<Item> itemList = new ArrayList<Item>();
		int i = 0;
		for(BuyRanking buyranking: rankingList) {
		    List<Item> item = itemRepository.findById(buyranking.getItemId());
		    itemList.add(item.get(0));
		    ranking[i] = item.get(0).getId();
		    i++;
		}

		//itemIdと対応するimgのHashMap
		HashMap<Integer,String> itemImg = Util.itemImg(itemList);

		//modelにセットする
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("itemList", itemList);
		model.addAttribute("ranking", ranking);

	    return "ranking/ranking";


	}

}
