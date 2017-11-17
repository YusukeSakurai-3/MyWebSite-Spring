package com.web.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.web.model.Item;

public class Util {


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
