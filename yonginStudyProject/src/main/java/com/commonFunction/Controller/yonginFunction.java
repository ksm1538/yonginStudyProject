package com.commonFunction.Controller;

import java.util.Calendar;

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
	
	/**
	 * 입력시간의 유효 여부를 확인
	 * @param     sTime 입력시간
	 * @return    유효 여부
	 */
	public static boolean validTime(String sTime) {
		String timeStr = validChkTime(sTime);

		Calendar cal;
		boolean ret = false;

		cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStr.substring(0, 2)));
		cal.set(Calendar.MINUTE, Integer.parseInt(timeStr.substring(2, 4)));

		String HH = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		String MM = String.valueOf(cal.get(Calendar.MINUTE));

		String pad2Str = "00";

		String retHH = (pad2Str + HH).substring(HH.length());
		String retMM = (pad2Str + MM).substring(MM.length());

		String retTime = retHH + retMM;

		if (sTime.equals(retTime)) {
			ret = true;
		}

		return ret;
	}
	
	/**
	 * 입력된 일자 문자열을 확인하고 8자리로 리턴
	 * @param sDate
	 * @return
	 */
	public static String validChkTime(String timeStr) {
		if (timeStr == null || !(timeStr.trim().length() == 4)) {
			throw new IllegalArgumentException("Invalid time format: " + timeStr);
		}
		
		if (timeStr.length() == 5) {
			timeStr = remove(timeStr, ':');
		}

		return timeStr;
	}
}
