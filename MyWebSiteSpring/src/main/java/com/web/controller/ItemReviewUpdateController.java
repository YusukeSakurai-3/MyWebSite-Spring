package com.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
public class ItemReviewUpdateController {


	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/itemreviewupdate", method = RequestMethod.GET)
	public String update(@RequestParam("updateId") String updateId,@ModelAttribute ItemReviewForm form,Model model){

		List<Review> reviewList = (List<Review>)reviewRepository.findById(Integer.parseInt(updateId));
		Review review = reviewList.get(0);

		List<Item> itemList = (List<Item>)itemRepository.findById(review.getItemId());
		Item item = itemList.get(0);



		//itemIdと対応するimgのHashMap
		String itemImg = "/img/"+item.getFileName();

		model.addAttribute("itemImg", itemImg);
		model.addAttribute("review", review);
		model.addAttribute("item", item);

	    return "item/itemreviewupdate";

	}

	@RequestMapping(value = "/itemreviewupdate", method = RequestMethod.POST)
	public String reviewupdate(@RequestParam("updateId") String updateId,@Validated @ModelAttribute ItemReviewForm form, BindingResult result,Model model,RedirectAttributes attribute) {

		System.out.println("updateId:"+updateId);
		//バリデイション
	    if (result.hasErrors()) {
			return update(updateId,form, model);
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

	    // セッションを取得
     	User user = (User) session.getAttribute("loginUser");


        //更新するレビューのインスタンス
        Review review = new Review(Integer.parseInt(updateId),form,user.getId());
        reviewRepository.save(review);



        attribute.addFlashAttribute("reviewActionMessage", "レビューを更新しました");


	    return "redirect:itemreviewlist";

        }

}
