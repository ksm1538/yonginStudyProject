package com.commonFunction.Controller;

public class yonginFunction {
	
	/**
	 * 해당 문자열이 비어있는지 확인
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 해당 문자열에서 삭제할 문자를 제거 후 반환
	 * @param str		원본 문자열
	 * @param remove	삭제할 문자(' , - , : 등) 
	 * @return
	 */
	public static String remove(String str, char remove) {
		if (isEmpty(str) || str.indexOf(remove) == -1) {
			return str;
		}
		char[] chars = str.toCharArray();
		int pos = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != remove) {
				chars[pos++] = chars[i];
			}
		}
		return new String(chars, 0, pos);
	}
	
	/**
	 * 8글자로 된 'YYYYMMDD' 문자열을 'YYYY-MM-DD'로 반환한다.
	 * @param date
	 * @return
	 */
	public static String addMinusChar(String date) {
		if (date.length() == 8) {
			return date.substring(0, 4).concat("-").concat(date.substring(4, 6)).concat("-").concat(date.substring(6, 8));
		} else {
			return "";
		}
	}
	
	/**
	 * 4글자로 된 'HHMM' 문자열을 'HH:MM'으로 반환한다.
	 * @param time
	 * @return
	 */
	public static String addColonChar(String time) {
		if (time != null) {
			return time.substring(0, 2).concat(":").concat(time.substring(2,4));
		} else {
			return "";
		}
	}
	
	/** 
	 * 널 값 확인
	 * @param src
	 * @return
	 */
	public static String nullConvert(String src) {

		if (src == null || src.equals("null") || "".equals(src) || " ".equals(src)) {
			return "";
		} else {
			return src.trim();
		}
	}
}
