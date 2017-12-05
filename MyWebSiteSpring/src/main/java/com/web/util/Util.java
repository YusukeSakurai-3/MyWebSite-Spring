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

public class Util {
	// ファイルをアップロードする場所
    public static final String UPLOAD_PAGE = "/Users/sakurai/git/MyWebSite-Spring/MyWebSiteSpring/bin/static/img/";


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


	//
	//
//		/**
//		 *
//		 *@param UserCreateForm
//		 *
//		 *@return Formからの情報を受け取ったUserクラス
//		 * */
//		public static User setUserByForm(UserCreateForm form){
//	       User user = new User();
//	       user.setLoginId(form.getLoginId());
//	       user.setName(form.getUserName());
//	       user.setPassword(Util.toCode(form.getPassword()));
//	       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	       // Date型変換
//	       Date formatDate = null;
//			try {
//				formatDate = sdf.parse(form.getBirthDate());
//			} catch (ParseException e) {
	//
//				e.printStackTrace();
//			}
//	       user.setBirthDate(formatDate);
//	       user.setAddress(form.getAddress());
//	       user.setIs_open(false);
//	       user.setLoginId(form.getLoginId());
//	       user.setCreateDate(new Timestamp(System.currentTimeMillis()));
//	       user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
	//
//			return user;
//		}


}
