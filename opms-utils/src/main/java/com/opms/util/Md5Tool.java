package com.opms.util;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Md5Tool {

	public static String getMd5(String password) {
		String str = "";
		if (password != null && !password.equals("")) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				Encoder base = Base64.getEncoder();
				// 加密后的字符串
				str = base.encodeToString(md.digest(password.getBytes("utf-8")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}
