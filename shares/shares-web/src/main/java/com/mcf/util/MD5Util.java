package com.mcf.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {
	public final static String MD5(String plaintext, String key) {
		MessageDigest messageDigest = null;
		try {
			plaintext += key;
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(plaintext.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	
	public final static String MD5ForSeaweedfs(String source) throws Exception {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
		byte[] btInput = source.getBytes("UTF-8");
		// 鑾峰緱MD5鎽樿绠楁硶鐨� MessageDigest 瀵硅薄
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		// 浣跨敤鎸囧畾鐨勫瓧鑺傛洿鏂版憳瑕�
		mdInst.update(btInput);
		// 鑾峰緱瀵嗘枃
		byte[] ciphertext = mdInst.digest();
		// 鎶婂瘑鏂囪浆鎹㈡垚鍗佸叚杩涘埗鐨勫瓧绗︿覆褰㈠紡
		int length=ciphertext.length;
		char chars[] = new char[length * 2];
		for (int i = 0; i < length; i++) {
			byte byte0 = ciphertext[i];
			chars[2*i] = hexDigits[byte0 >>> 4 & 0xf];
			chars[2*i+1] = hexDigits[byte0 & 0xf];
		}
		return new String(chars);
	}
	
	public static void main(String[] args) throws Exception {
		String  string= "dd";
		String str= str = MD5ForSeaweedfs(string);

		System.out.println(str);
		System.out.println(Byte.toString(new Integer(0xf).byteValue()));
		System.out.println(new BigInteger(new Integer(0xf).toString()).toString(2));
	}
}
