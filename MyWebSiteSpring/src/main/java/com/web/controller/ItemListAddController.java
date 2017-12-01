package com.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.model.ItemGetList;
import com.web.model.User;
import com.web.repository.ItemGetListRepository;
import com.web.repository.ItemRepository;

@Controller
public class ItemListAddController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemGetListRepository itemGetListRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemlistadd", method = RequestMethod.GET)
	public String listadd(@RequestParam(name = "itemGetId") String itemGetId,
			                 Model model,RedirectAttributes attribute ) {

		User user = (User)session.getAttribute("loginUser");
		//選択された商品
		int itemId = Integer.parseInt(itemGetId);
		ItemGetList getItem = new ItemGetList(user.getId(),itemId);
		itemGetListRepository.save(getItem);


	    return "redirect:itemgetlist";


	}

}
