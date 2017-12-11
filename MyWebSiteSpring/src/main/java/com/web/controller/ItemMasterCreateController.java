package com.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.ItemCreateForm;
import com.web.model.Item;
import com.web.repository.ItemRepository;
import com.web.util.Util;

@Controller
public class ItemMasterCreateController {

	@Autowired
	ItemRepository itemRepository;


	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemmastercreate", method = RequestMethod.GET)
	//バリデーションとか書く
	public String itemmaster(@ModelAttribute ItemCreateForm form,Model model){

	    return "item/itemmastercreate";

	}

	@RequestMapping(value = "/itemmastercreate", method = RequestMethod.POST)
	public String itemmastercreate(@Validated @ModelAttribute ItemCreateForm form, BindingResult result, Model model,RedirectAttributes attribute ){
		//バリデイション
		if (result.hasErrors()) {
			return itemmaster(form, model);
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


     	//ファイルがない場合noimage.pngを登録する
        //登録する商品のインスタンス
        Item item = new Item(form);
        itemRepository.save(item);

		attribute.addFlashAttribute("itemActionMessage", "新しい商品を登録しました");
	    return "redirect:itemmaster";

	}

}
