package com.web.controller;

import java.util.ArrayList;
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
import com.web.model.ItemGetList;
import com.web.model.User;
import com.web.repository.ItemGetListRepository;
import com.web.repository.ItemRepository;
import com.web.repository.UserRepository;
import com.web.util.Util;

@Controller
public class ItemGetListController {


	//1ページに表示する商品数
	final static int PAGE_MAX_ITEM_COUNT = 8;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemGetListRepository itemGetListRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemgetlist", method = RequestMethod.GET)
	public String itemgetlist(@RequestParam(name = "userId",defaultValue="0") String inputUserId,
				                       Model model,RedirectAttributes attribute ) {



		int userId = Integer.parseInt(inputUserId);
		//Idが指定されていない場合、ログインユーザーのほしい物リストのページに行く
		if(inputUserId.equals("0")) {
			User User = (User)session.getAttribute("loginUser");
			userId = User.getId();
		}
		System.out.println(userId);

		ArrayList<Item> itemGetList = new ArrayList<Item>();
		List<ItemGetList> getList = itemGetListRepository.findByUserId(userId);
		User listUser = userRepository.findById(userId);
		for(ItemGetList getItem :getList) {
			List<Item> item = itemRepository.findById(getItem.getItemId());
			itemGetList.add(item.get(0));
		}

		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemGetList);

		//modelにセットする
		model.addAttribute("user", listUser);
		model.addAttribute("itemImg", itemImg);
		model.addAttribute("itemGetList", itemGetList);

		return "item/itemgetlist";


	}
}
