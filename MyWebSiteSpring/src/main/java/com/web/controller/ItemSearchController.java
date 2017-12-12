package com.web.controller;

import java.util.HashMap;
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

import com.web.form.ItemSearchForm;
import com.web.model.Item;
import com.web.repository.ItemRepository;
import com.web.util.Paging;
import com.web.util.Util;

@Controller
public class ItemSearchController {
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 8;



	@Autowired
	ItemRepository itemRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemSearchDetail", method = RequestMethod.GET)
	public String searchDetail(ItemSearchForm form,@RequestParam(name = "pageNum",defaultValue = "0") String pageN,
			Model model,RedirectAttributes attribute ) {
		int startCheck = Util.numCheck(form.getStartPrice());
		int endCheck = Util.numCheck(form.getEndPrice());

		if(startCheck==Util.STRING_NUM||endCheck==Util.STRING_NUM) {
			attribute.addFlashAttribute("msg","検索エラー:数値を入力してください");
			return "redirect:index";
		}

		if(startCheck==Util.MINUS_NUM||endCheck==Util.MINUS_NUM) {
			attribute.addFlashAttribute("msg","検索エラー:正の値を入力してください");
			return "redirect:index";
		}



		Boolean detail = true;
		Boolean start =  Util.numCheck(form.getStartPrice())!= Util.NULL_NUM;
		Boolean end =  Util.numCheck(form.getEndPrice())!= Util.NULL_NUM;


		System.out.println("価格検索:"+form.getStartPrice());

		int pageNum = Integer.parseInt(pageN);




		Page<Item> itemPage = itemRepository.findByNameContains(form.getSearchWord(),new PageRequest(pageNum, PAGE_MAX_ITEM_COUNT));


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

		//商品,ページ数を取得
		List<Item> itemList = itemPage.getContent();
		int maxPage = itemPage.getTotalPages();
		System.out.println(maxPage);

		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemList);

		//ページング処理用
		Paging page = new Paging(pageNum, maxPage);

		//modelにセットする
		model.addAttribute("page",page);
		model.addAttribute("form",form);
		model.addAttribute("detail",detail);
		model.addAttribute("itemPage",itemPage);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageMax",maxPage);
		model.addAttribute("index", "index");
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("itemList", itemList);

	    return "search/result";


	}

	@RequestMapping(value = "/itemSearchWord", method = RequestMethod.GET)
	public String searchword(@RequestParam(name = "searchWord") String searchWord,@RequestParam(name = "pageNum",defaultValue = "0") String pageN,
			Model model,RedirectAttributes attribute ) {

		Boolean detail = false;

		int pageNum = Integer.parseInt(pageN);

		Page<Item> itemPage = itemRepository.findByNameContains(searchWord,new PageRequest(pageNum, PAGE_MAX_ITEM_COUNT));
		List<Item> itemList = itemPage.getContent();
		int maxPage = itemPage.getTotalPages();
		System.out.println(maxPage);

		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemList);


		//ページング処理用
		Paging page = new Paging(pageNum, maxPage);

		//modelにセットする
		model.addAttribute("page",page);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("detail",detail);
		model.addAttribute("itemPage",itemPage);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageMax",maxPage);
		model.addAttribute("index", "index");
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("itemList", itemList);

	    return "search/result";


	}
}
