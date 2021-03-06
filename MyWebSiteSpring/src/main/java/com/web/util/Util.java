package com.web.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.web.model.BuyDetail;
import com.web.model.Item;
import com.web.model.Review;

public class Util {
	// ファイルをアップロードする場所
    public static final String UPLOAD_PAGE = "/Users/sakurai/git/MyWebSite-Spring/MyWebSiteSpring/bin/static/img/";
    public static final int MINUS_NUM = -3;
    public static final int STRING_NUM = -2;
    public static final int NULL_NUM = -1;



	/**商品のListに対してfileNameをhtmlで読み込める形にして返す
	 *
	 *@param List<Item>
	 *
	 *@return 商品Idと修正したfileNameのHashMap
	 * */
	public static HashMap<Integer,String> itemImg(List<Item> itemList){

		HashMap<Integer,String> itemImg = new HashMap<Integer,String>();

		for(Item item : itemList) {
			String img = "/img/"+item.getFileName();
			itemImg.put(item.getId(),img);
		}
		return itemImg;
	}

	/**レビューのListに対してfileNameをhtmlで読み込める形にして返す
	 *
	 *@param List<Item>
	 *
	 *@return 商品Idと修正したfileNameのHashMap
	 * */
	public static HashMap<Integer,String> reviewImg(List<Review> reviewList){

		HashMap<Integer,String> itemImg = new HashMap<Integer,String>();

		for(Review review : reviewList) {
			String img = "/img/"+review.getFileName();
			itemImg.put(review.getId(),img);
		}
		return itemImg;
	}



	/**暗号メソッド
	 * @param  source ;
	 * @return 暗号化された文字列
	 */
	public static String toCode(String source) {


		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);
			return result;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**商品の合計を計算するメソッド
	 * @param  合計したい商品リスト
	 * @return 合計金額
	 */
	public static int getTotalItemPrice(ArrayList<Item> itemList) {
		int total = 0;
		for(Item item : itemList) {
			total += item.getPrice();
		}

		return total;
	}

	/**データベースに登録したい購入情報の詳細リストを取得する
	 * @param  商品リスト
	 * @param  購入Id
	 * @return 購入情報詳細リスト
	 */
	public static ArrayList<BuyDetail> setBuyItemList(ArrayList<Item> itemList, int buyId) {

		ArrayList<BuyDetail> buyItemList = new ArrayList<BuyDetail>();
		for(Item item:itemList) {
			BuyDetail buydetail = new BuyDetail(item.getId(),buyId);
			buyItemList.add(buydetail);
		}
		return buyItemList;
	}

	/**
	 * 適切にな長さの部分文字列
	 */
	public static String getsubString(String str, int endIndex) {
		if(str.length() < endIndex) {
			return str;
		}else {
			return str.substring(0, endIndex - 1)+"⋯";
		}
	}

	/**
	 * 文字を数値に変換する
	 * @param value 構文解析対象の int 表現を含む String
	 *
	 */
	public static int numCheck(String value) {

	    try {
	        // intに変換して返す
	    		int num = Integer.parseInt(value);
	    		if (num < 0)
	    			return MINUS_NUM;
	    		else
	    			return Integer.parseInt(value);

	    } catch ( NumberFormatException e ) {
	    	if(value.length()!=0) {
	    		return STRING_NUM;
	    	}else {

	        return NULL_NUM;
	    	}
	  }
	}





}
