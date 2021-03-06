package com.webtoon.external;

import java.util.Calendar;
import java.util.Hashtable;

public class CalculateDate {
	static Calendar cal = Calendar.getInstance();

	public static int year = cal.get(Calendar.YEAR);
	public static int month = cal.get(Calendar.MONTH) + 1;
	public static int date = cal.get(Calendar.DATE);
	public static int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

	public static int hour = cal.get(Calendar.HOUR);
	public static int minute = cal.get(Calendar.MINUTE);
	public static int second = cal.get(Calendar.SECOND);

	public static final String[] dayOfWeekEng = { "sun", "mon", "tue", "wed", "thu", "fri", "sat" };
	public static final String[] dayOfWeekKor = { "일", "월", "화", "수", "목", "금", "토" };

	public static int calcDayOfWeek(String language, String choosedDay) throws Exception {
		Hashtable<String, Integer> hash = new Hashtable<String, Integer>();

		if(language.equals("kor")) {
			for (int i = 0; i < 7; i++) {
				hash.put(dayOfWeekKor[i], ((i + 1) + 5) % 7);
			}
		}else if(language.equals("eng")) {
			for (int i = 0; i < 7; i++) {
				hash.put(dayOfWeekEng[i], ((i + 1) + 5) % 7);
			}
		}else {
		}
		
		if (choosedDay == null)
			return ((dayOfWeek + 5) % 7);
		else
			return hash.get(choosedDay);

	}
	
	public static String calcTodayYoill(int num, String language) throws Exception {
		Hashtable<Integer, String> hash = new Hashtable<Integer, String>();
		
		if(language.equals("kor")) {
			for (int i = 0; i < 7; i++) {
				hash.put(i, dayOfWeekKor[i]);
			}
		}else if(language.equals("eng")) {
			for (int i = 0; i < 7; i++) {
				hash.put(i, dayOfWeekEng[i]);
			}
		}else {
		}

		return hash.get(num);

	}
}

// 출처: https://jamesdreaming.tistory.com/94
