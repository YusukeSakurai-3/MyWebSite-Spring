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

import com.web.form.ItemReviewForm;
import com.web.model.Item;
import com.web.model.Review;
import com.web.model.User;
import com.web.repository.ItemRepository;
import com.web.repository.ReviewRepository;
import com.web.util.Util;

@Controller
public class ItemReviewController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemreview", method = RequestMethod.GET)
	public String itemreview(@RequestParam("itemId") String itemId, @ModelAttribute ItemReviewForm form,Model model){

		List<Item> itemList = (List<Item>)itemRepository.findById(Integer.parseInt(itemId));
		Item item = itemList.get(0);


		//itemIdと対応するimgのHashMap
		HashMap<Integer, String> itemImg = Util.itemImg(itemList);

		model.addAttribute("itemImg", itemImg);
		model.addAttribute("item", item);

	    return "item/itemreview";


	}

	@RequestMapping(value = "/itemreview", method = RequestMethod.POST)
	public String add(@Validated @ModelAttribute ItemReviewForm form, BindingResult result, Model model,RedirectAttributes attribute ) {
		//バリデイション
		if (result.hasErrors()) {
			return itemreview(form.getItemId(),form, model);
		}

		System.out.println("evaluation:"+form.getEvaluation());
		System.out.println("itemId:"+form.getItemId());
		System.out.println("reviewText:"+form.getReviewText());
		System.out.println("Title:"+form.getTitle());
        System.out.println(form.getUploadFile().getOriginalFilename());

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


        // セッションを取得
     	User user = (User) session.getAttribute("loginUser");
     	//ファイルがない場合noimage.imgを保存
        //登録するレビューのインスタンス
        Review review = new Review(form, user.getId());
        reviewRepository.save(review);

        attribute.addFlashAttribute("reviewActionMessage", "レビューを追加しました");


	    return "redirect:itemreviewlist";

        }
}
