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
}
