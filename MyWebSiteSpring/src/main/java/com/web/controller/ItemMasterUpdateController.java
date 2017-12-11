package com.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.ItemUpdateForm;
import com.web.model.Item;
import com.web.repository.ItemRepository;
import com.web.util.Util;

@Controller
public class ItemMasterUpdateController {

	@Autowired
	ItemRepository itemRepository;


	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemmasterupdate", method = RequestMethod.GET)
	public String itemmaster(@RequestParam("itemId") Integer itemId, @ModelAttribute ItemUpdateForm form,Model model){


		List<Item> itemList = (List<Item>)itemRepository.findById(itemId);
		Item item = itemList.get(0);

		HashMap<Integer, String> itemImg = Util.itemImg(itemList);


		model.addAttribute("item", item);
		model.addAttribute("itemImg", itemImg);

	    return "item/itemmasterupdate";

	}

	@RequestMapping(value = "/itemmasterupdate", method = RequestMethod.POST)
	public String itemmasterupdate(@Validated @ModelAttribute ItemUpdateForm form, BindingResult result, Model model,RedirectAttributes attribute ){
		System.out.println(form.getId());
		//バリデイション
		if (result.hasErrors()) {
			return itemmaster(form.getId(),form, model);
		}

		//ファイルが選択されている場合
        if(!form.getUploadFile().isEmpty()){

	        String filename = form.getUploadFile().getOriginalFilename();

	        //画像をアップロードする
	        Path uploadfile = Paths
	             .get(Util.UPLOAD_PAGE + filename);

	        try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
	          byte[] bytes = form.getUploadFile().getBytes();
	          os.write(bytes);
	        } catch (IOException ex) {
	          System.err.println(ex);
	        }
        }

     	//ファイルがない場合元のファイルを登録する
        //登録する商品のインスタンス
        Item item = new Item(form);
        itemRepository.save(item);

		attribute.addFlashAttribute("itemActionMessage", "商品情報を更新しました");
	    return "redirect:itemmaster";

	}

}
