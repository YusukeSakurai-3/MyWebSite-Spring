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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.SearchForm;
import com.web.model.Item;
import com.web.repository.BuyDetailRepository;
import com.web.repository.ItemRepository;
import com.web.util.Paging;
import com.web.util.Util;

@Controller
public class ItemMasterController {
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 20;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	BuyDetailRepository buyDetailRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemmaster", method = RequestMethod.GET)
	public String itemmaster(@RequestParam(name="searchWord",defaultValue="") String itemName,
			@RequestParam(name="startPrice",defaultValue = "") String startPrice,
			@RequestParam(name="endPrice", defaultValue= "") String endPrice,
			@RequestParam(name = "pageNum",defaultValue = "0") String pageN,Model model,RedirectAttributes attribute){

		SearchForm form = new SearchForm(itemName,startPrice,endPrice);


		int startCheck = Util.numCheck(form.getStartPrice());
		int endCheck = Util.numCheck(form.getEndPrice());

		if(startCheck==Util.STRING_NUM||endCheck==Util.STRING_NUM) {
			attribute.addFlashAttribute("msg","検索エラー:数値を入力してください");
			return "redirect:itemmaster";
		}

		if(startCheck==Util.MINUS_NUM||endCheck==Util.MINUS_NUM) {
			attribute.addFlashAttribute("msg","検索エラー:正の値を入力してください");
			return "redirect:itemmaster";
		}

		Boolean start =  Util.numCheck(form.getStartPrice())!= Util.NULL_NUM;
		Boolean end =  Util.numCheck(form.getEndPrice())!= Util.NULL_NUM;

		int pageNum = Integer.parseInt(pageN);


		//商品を取得
		Page<Item> itemPage = (Page<Item>)itemRepository.findByNameContains(itemName,new PageRequest(pageNum, PAGE_MAX_ITEM_COUNT));


		if(start&&!end) {
		    itemPage = itemRepository.findByNameContainsAndPriceGreaterThanEqual(form.getSearchWord(),
				Integer.parseInt(form.getStartPrice()),new PageRequest(pageNum, PAGE_MAX_ITEM_COUNT));
		}
		else if(start&&end) {
		    itemPage = itemRepository.findByNameContainsAndPriceGreaterThanEqualAndPriceLessThanEqual(form.getSearchWord(),
				Integer.parseInt(form.getStartPrice()),
				Integer.parseInt(form.getEndPrice()),new PageRequest(pageNum, PAGE_MAX_ITEM_COUNT));
		}
		else  if(!start&&end){
			itemPage = itemRepository.findByNameContainsAndPriceLessThanEqual(form.getSearchWord(),
					Integer.parseInt(form.getEndPrice()),new PageRequest(pageNum, PAGE_MAX_ITEM_COUNT));
		}


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



		System.out.println(totalElements);

		//ページング処理用
		Paging page = new Paging(pageNum, maxPage);

		model.addAttribute("searchWord",itemName);
		model.addAttribute("page",page);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("form",form);
		model.addAttribute("itemList", itemList);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageMax",maxPage);

	    return "item/itemmaster";

	}


}
