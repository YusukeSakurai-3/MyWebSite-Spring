package com.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.ItemSearchForm;
import com.web.model.Item;
import com.web.repository.ItemRepository;
import com.web.util.Util;

@Controller
public class ItemSearchController {
	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 8;


	@Autowired
	ItemRepository itemRepository;

	@Autowired
	HttpSession session;

	//@RequestParam("searchWord")
	@RequestMapping(value = "/itemSearch", method = RequestMethod.GET)
	public String search(@Validated ItemSearchForm form,BindingResult result, Model model,RedirectAttributes attribute){
		if(result.hasErrors()) {
			 attribute.addFlashAttribute("msg", "検索エラー");
			 return "redirect:index";
		}
		String[] searchWords = form.getSearchWord().split("　", 0);

		for(String str:searchWords) {
			System.out.println(str);
		}
//		System.out.println(form.getSearchWord());


		Page<Item> itemPage = itemRepository.findByNameContains(form.getSearchWord(),new PageRequest(1, PAGE_MAX_ITEM_COUNT));
		List<Item> itemList = itemPage.getContent();

		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemList);

		//modelにセットする
		model.addAttribute("index", "index");
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("itemList", itemList);

	    return "search/result";


	}
}
