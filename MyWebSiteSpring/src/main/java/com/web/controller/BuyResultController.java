package com.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.model.Buy;
import com.web.model.BuyDetail;
import com.web.model.DeliveryMethod;
import com.web.model.Item;
import com.web.model.Point;
import com.web.model.User;
import com.web.repository.BuyDetailRepository;
import com.web.repository.BuyRepository;
import com.web.repository.PointRepository;
import com.web.util.Util;

@Controller
public class BuyResultController {


	@Autowired
	private BuyDetailRepository buyDetailRepository;

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private BuyRepository buyRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/buyresult", method = RequestMethod.POST)
	public String buy(Model model,RedirectAttributes attribute) {
		// セッションを取得
		User user = (User) session.getAttribute("loginUser");
		//セッションから購入商品情報であるカート情報を取得
		ArrayList<Item> buyItemData = (ArrayList<Item>)session.getAttribute("cart");
		//購入情報を取得
		Buy buyData = (Buy)session.getAttribute("buyData");
		Point pointData = (Point)session.getAttribute("pointData");
		DeliveryMethod deliveryMethod = (DeliveryMethod)session.getAttribute("deliveryMethod");

		//ユーザーのポイント情報を更新
		pointRepository.save(pointData);


	    //ユーザーの購入情報を登録する
	    //現在時刻の取得
	    buyData.setCurrentDate();
	    buyRepository.save(buyData);
	    //buyRepository.flush(buyData);


	    //System.out.println("buyData"+buyData.getId());
	    //購入IDを取得
	    int buyId = buyData.getId();


	   // 購入詳細情報を購入情報IDに紐づけして登録
	    ArrayList<BuyDetail> buyItemList = Util.setBuyItemList(buyItemData,buyId);
	    for (BuyDetail buyDetail : buyItemList) {
	    	    buyDetailRepository.save(buyDetail);
	    }


	    	//セッションを削除する
	    session.invalidate();

	    	//ユーザーのセッションを再度セットする
	    	session.setAttribute("loginUser",user);

	    	//購入詳細情報
	    	model.addAttribute("buyItemData",buyItemData);
	    	model.addAttribute("buyData", buyData);
	    model.addAttribute("deliveryMethod", deliveryMethod);


		return "buy/buyresult";


	}

}
