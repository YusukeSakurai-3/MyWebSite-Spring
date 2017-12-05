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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.form.ItemReviewForm;
import com.web.model.Review;
import com.web.model.User;
import com.web.repository.ReviewRepository;
import com.web.util.Util;

@RequestMapping("/itemreviewadd")
@Controller
public class ItemReviewAddController {
	@Autowired
	HttpSession session;

	@Autowired
	ReviewRepository reviewRepository;

	@PostMapping
	public String result(@Validated @ModelAttribute ItemReviewForm form, BindingResult result, Model model,RedirectAttributes attribute ) {

		System.out.println("evaluation:"+form.getEvaluation());
		System.out.println("itemId:"+form.getItemId());
		System.out.println("reviewText:"+form.getReviewText());
		System.out.println("Title:"+form.getTitle());
        System.out.println(form.getUploadFile().getOriginalFilename());

        String filename = form.getUploadFile().getOriginalFilename();

        //ファイルがない場合noimage.img
        //画像をアップロードする
        Path uploadfile = Paths
             .get(Util.UPLOAD_PAGE + filename);

        try (OutputStream os = Files.newOutputStream(uploadfile, StandardOpenOption.CREATE)) {
          byte[] bytes = form.getUploadFile().getBytes();
          os.write(bytes);
        } catch (IOException ex) {
          System.err.println(ex);
        }

        // セッションを取得
     	User user = (User) session.getAttribute("loginUser");
     	//ファイルがない場合noimage.imgを保存
        //登録するレビューのインスタンス
        Review review = new Review(form, user.getId());
        reviewRepository.save(review);

	    return "redirect:/index";

        }


}