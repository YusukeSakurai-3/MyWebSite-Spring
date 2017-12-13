package com.web.controller;

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

@Controller
public class UserItemDeleteController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemGetListRepository itemGetListRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/useritemdelete", method = RequestMethod.GET)
	public String useritemdelete(@RequestParam(name = "deleteItemIdList") String[] deleteIdList,Model model,RedirectAttributes attribute ) {


		User loginUser = (User)session.getAttribute("loginUser");

		for(String id:  deleteIdList) {
			List<ItemGetList> item = itemGetListRepository.findByItemIdAndUserId(Integer.parseInt(id),loginUser.getId());

			itemGetListRepository.delete(item);
			List<Item> deleteItem = itemRepository.findById(Integer.parseInt(id));
			System.out.println(deleteItem.get(0).getName());
		}
		//modelにセットする
//		model.addAttribute("user",);
		attribute.addFlashAttribute("listMessage", "選択された商品を削除しました");


		return "redirect:itemgetlist";


	}

}
