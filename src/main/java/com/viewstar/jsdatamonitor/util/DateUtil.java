package com.viewstar.jsdatamonitor.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期工具类
 *
 * @author liwei
 */
public class DateUtil {
	/**
	 * 得到当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws Exception
	 */
	public String getCurDayTime() throws Exception {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 转换日期格式
	 *
	 * @param dateStr
	 *            日期字符串
	 * @param origPattern
	 *            日期字符串的格式
	 * @param destPattern
	 *            要转换的日期格式
	 * @return 转换后的日期字符串
	 */
	public String convertFormat(String dateStr, String origPattern, String destPattern) {
		if (dateStr == null) {
			return "";
		}
		SimpleDateFormat origSdf = new SimpleDateFormat(origPattern);
		Date date = null;
		try {
			date = origSdf.parse(dateStr);
		} catch (ParseException e) {
			StringBuilder errMsg = new StringBuilder();
			errMsg.append("Can not convert");
			errMsg.append(dateStr);
			errMsg.append("to Date with");
			errMsg.append(origPattern);
			errMsg.append(".");
			throw new IllegalArgumentException(errMsg.toString(), e);
		}

		SimpleDateFormat destSdf = new SimpleDateFormat(destPattern);

		return destSdf.format(date);
	}

	/**
	 * 时间格式 h:m:s和秒数相加
	 *
	 * @param seconds
	 *            秒数
	 * @param hours
	 *            h:m:s
	 * @return h:m:s
	 */
	public String sum(String seconds, String hours) {
		return sumHour(Second2Hour(seconds), hours);
	}

	/**
	 * 两个时间格式 h:m:s相加
	 *
	 * @param hours1
	 *            h:m:s
	 * @param hours2
	 *            h:m:s
	 * @return h:m:s
	 */
	public String sumHour(String hours1, String hours2) {
		String[] h1 = hours1.split(":");
		String[] h2 = hours2.split(":");
		long add = 0;
		long sec = (Long.parseLong(h1[2]) + Long.parseLong(h2[2])) % 60;
		add = (Long.parseLong(h1[2]) + Long.parseLong(h2[2])) / 60;
		long min = (Long.parseLong(h1[1]) + Long.parseLong(h2[1]) + add) % 60;
		add = (Long.parseLong(h1[1]) + Long.parseLong(h2[1]) + add) / 60;
		long hour = Long.parseLong(h1[0]) + Long.parseLong(h2[0]) + add;
		return hour + ":" + min + ":" + sec;
	}

	/**
	 * 两个时间格式 hh:mm:ss相加
	 *
	 * @param hours1
	 *            hh:mm:ss
	 * @param hours2
	 *            hh:mm:ss
	 * @return hh:mm:ss
	 */
	public static String sumHourMinuteSec(String hours1, String hours2) {
		String[] h1 = hours1.split(":");
		String[] h2 = hours2.split(":");
		long add = 0;
		long sec = (Long.parseLong(h1[2]) + Long.parseLong(h2[2])) % 60;
		add = (Long.parseLong(h1[2]) + Long.parseLong(h2[2])) / 60;
		long min = (Long.parseLong(h1[1]) + Long.parseLong(h2[1]) + add) % 60;
		add = (Long.parseLong(h1[1]) + Long.parseLong(h2[1]) + add) / 60;
		long hour = Long.parseLong(h1[0]) + Long.parseLong(h2[0]) + add;
		String h = String.valueOf(hour).length() == 1 ? "0" + hour : String.valueOf(hour);
		String m = String.valueOf(min).length() == 1 ? "0" + min : String.valueOf(min);
		String s = String.valueOf(sec).length() == 1 ? "0" + sec : String.valueOf(sec);
		return h + ":" + m + ":" + s;
	}


	/**
	 * 把秒数转换成时间格式 h:m:s
	 *
	 * @param seconds
	 * @return h:m:s
	 */
	public String Second2Hour(String seconds) {
		int s = Integer.parseInt(seconds);
		int hour = s / 3600;
		int min = (s - hour * 3600) / 60;
		int sec = s - hour * 3600 - min * 60;
		return hour + ":" + min + ":" + sec;
	}

	/**
	 * 当前日期加上天数
	 *
	 * @param time
	 *            当前日期
	 * @param pattern
	 *            日期格式
	 * @param day
	 *            加上的天数
	 * @return 当前日期加上天数的日期
	 * @throws Exception
	 */
	public String addDay(String time, String pattern, int day) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(time));
		c.add(Calendar.DATE, day);
		return df.format(c.getTime());
	}
	/**
	 * 当前日期加上分钟数
	 *
	 * @param time
	 *            当前日期
	 * @param pattern
	 *            日期格式
	 * @param day
	 *            加上的天数
	 * @return 当前日期加上分钟数的日期
	 * @throws Exception
	 */
	public String addMinute(String time, String pattern, int day) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(time));
		c.add(Calendar.MINUTE, day);
		return df.format(c.getTime());
	}
	/**
	 * 当前日期加上天数，日期格式为yyyy-MM-dd
	 *
	 * @param date
	 *            当前日期
	 * @param day
	 *            加上的天数
	 * @return 当前日期加上天数的日期
	 * @throws Exception
	 */
	public String getTime(String date, int day) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(sf.parse(date));
		c.add(java.util.Calendar.DATE, day);
		return sf.format(c.getTime());
	}

	/**
	 * 当前日期加上月数
	 *
	 * @param date
	 *            当前日期
	 * @param month
	 *            月数
	 * @return 当前日期加上月数的日期
	 * @throws Exception
	 */
	public String getTimeMonth(String date, int month) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(sf.parse(date));
		c.add(java.util.Calendar.MONTH, month);
		return sf.format(c.getTime());
	}

	/**
	 * 日期转换为中文的星期
	 *
	 * @param date
	 *            日期
	 * @return 中文的星期
	 * @throws Exception
	 */
	public String dateToWeekDay(String date) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "星期一");
		map.put("2", "星期二");
		map.put("3", "星期三");
		map.put("4", "星期四");
		map.put("5", "星期五");
		map.put("6", "星期六");
		map.put("0", "星期日");
		return map.get(String.valueOf(sf.parse(date).getDay())).toString();
	}

	/**
	 * 得到两个日期之间的日期列表
	 *
	 * @param beginTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return 日期列表yyyy-MM-dd
	 * @throws Exception
	 */
	public List<String> getTimeSpan(String beginTime, String endTime) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(sf.parse(beginTime));
		while (!sf.parse(endTime).before(cal.getTime())) {
			list.add(sf.format(cal.getTime()));
			cal.add(cal.DATE, 1);
		}
		return list;
	}

	/**
	 * 得到两个时间之间的小时
	 *
	 * @param beginTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @param strdate
	 *            指定天
	 * @return 小时列表
	 * @throws Exception
	 */
	public List<String> getTimeSpanHour(String beginTime, String endTime, String strdate) throws Exception {
		DecimalFormat decimalFormat = new DecimalFormat("00");
		List<String> list = new ArrayList<String>();
		String beginDate = beginTime.substring(0, 8);
		String endDate = endTime.substring(0, 8);
		if (Long.valueOf(strdate) > Long.valueOf(beginDate)) {
			beginTime = strdate + "000000";
		}
		if (Long.valueOf(strdate) < Long.valueOf(endDate)) {
			endTime = strdate + "235959";
		}
		int beginIndex = Integer.parseInt(beginTime.substring(8, 10));
		int endIndex = Integer.parseInt(endTime.substring(8, 10));
		for (int i = beginIndex; i < endIndex; i++) {
			list.add(strdate + decimalFormat.format(i) + "00");
		}
		if (Integer.parseInt(endTime.substring(10)) != 0) {
			list.add(strdate + decimalFormat.format(endIndex) + "00");
		}
		return list;
	}

	public static void main(String[] s) throws Exception{
		DateUtil dateUtil = new DateUtil();
		System.out.println(dateUtil.getTimeSpanHour("20151104200301", "20151104190201", "20151104"));
	}

	/**
	 * 得到当前日期加上或减去月份数的那个月的第一天
	 *
	 * @param date
	 *            当前日期
	 * @param month
	 *            月份数
	 * @return 当前日期加上或减去月份数的那个月的第一天
	 */
	public String getFristDayOfMonth(Date date, int month) {
		Date d = new Date();
		d.setTime(date.getTime());
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, month);
		d = c.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		c.set(Calendar.DAY_OF_MONTH, 1);
		return sf.format(c.getTime());
	}

	/**
	 * 得到当前日期加上或减去月份数的那个月的第一天
	 *
	 * @param date
	 *            当前日期
	 * @param month
	 *            月份数
	 * @return 当前日期加上或减去月份数的那个月的第一天
	 */
	public String getFristDayOfMonth(String date, int month) {
		Date d = new Date();
		try {
			d.setTime(formatDate(date, "yyyy-MM-dd").getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, month);
		d = c.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		c.set(Calendar.DAY_OF_MONTH, 1);
		return sf.format(c.getTime());
	}

	/**
	 * 得到当前日期加上或减去月份数的那个月的第一天
	 *
	 * @param date
	 *            当前日期
	 * @param month
	 *            月份数
	 * @return 当前日期加上或减去月份数的那个月的第一天MM/dd/yyyy
	 */
	public String getFristDayOfMonth2(Date date, int month) {
		Date d = new Date();
		d.setTime(date.getTime());
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, month);
		d = c.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
		c.set(Calendar.DAY_OF_MONTH, 1);
		return sf.format(c.getTime());
	}

	/**
	 * 得到当前日期加上或减去月份数的那个月的最后一天
	 *
	 * @param date
	 *            当前日期
	 * @param month
	 *            月份数
	 * @return 当前日期加上或减去月份数的那个月的最后一天
	 */
	public String getLastDayOfMonth(Date date, int month) {
		Date d = new Date();
		d.setTime(date.getTime());
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, month);
		d = c.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		return sf.format(c.getTime());
	}

	/**
	 * 得到当前日期加上或减去月份数的那个月的最后一天
	 *
	 * @param date
	 *            当前日期
	 * @param month
	 *            月份数
	 * @return 当前日期加上或减去月份数的那个月的最后一天MM/dd/yyyy
	 */
	public String getLastDayOfMonth2(Date date, int month) {
		Date d = new Date();
		d.setTime(date.getTime());
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, month);
		d = c.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		return sf.format(c.getTime());
	}

	/**
	 * 获取上周的周一和周日日期.
	 *
	 * @return 周一日期,周日日期
	 */
	public String getDayofLastWeek() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date today1 = new Date();
		try {
			today1 = sf.parse("2013-03-24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(today1);
		String monday;
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
		cal.add(Calendar.DATE, 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		monday = monday + "," + new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
		return monday;
	}

	/**
	 * 获取同比上上周日期.
	 *
	 * @return 上周周一日期，上周周日日期
	 */
	public String getDayofLast2Week() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date today1 = new Date();
		try {
			today1 = sf.parse("2013-03-24");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(today1);
		String monday;
		cal1.add(Calendar.DATE, -14);
		cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = new SimpleDateFormat("MM/dd/yyyy").format(cal1.getTime());
		cal1.add(Calendar.DATE, 7);
		cal1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		monday = monday + "," + new SimpleDateFormat("MM/dd/yyyy").format(cal1.getTime());
		return monday;
	}
	/**
	 * 获取上周的周一日期.
	 *
	 * @return 周一日期
	 */
	public String getDayofLastWeekMon() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date today1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today1);
		String monday;
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = sf.format(cal.getTime());
		return monday;
	}
	/**
	 * 获取上周的周日日期.
	 *
	 * @return 周日日期
	 */
	public String getDayofLastWeekSun() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date today1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today1);
		String sunday;
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sunday = sf.format(cal.getTime());
		return sunday;
	}
	/**
	 * 指定年和月，加上指定的天数
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param relativeRadioNum
	 *            指定的天数
	 * @return 得到的日期
	 */
	public String[] getTimes(String year, String month, String relativeRadioNum) {
		String[] time = new String[2];
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DecimalFormat df = new DecimalFormat("00");
			Date d = sf.parse(year + df.format(Integer.valueOf(month)));
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.SECOND, -1);
			String beginTime = sf1.format(c.getTime());
			c.setTime(d);
			for (int i = 0; i < Integer.parseInt(relativeRadioNum); i++) {
				c.add(Calendar.MONTH, -1);
			}
			String endTime = sf1.format(c.getTime());
			time[0] = endTime;
			time[1] = beginTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 指定年和月，加上指定的月数
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param relativeRadioNum
	 *            指定的月数
	 * @return 得到的日期
	 */
	public String[][] getNumMonthTimes(String year, String month, String relativeRadioNum) {
		String[][] time = new String[Integer.parseInt(relativeRadioNum) + 1][2];
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df = new DecimalFormat("00");
			Date d = sf.parse(year + df.format(Integer.valueOf(month)));
			for (int i = 0; i < Integer.parseInt(relativeRadioNum) + 1; i++) {
				Calendar c = Calendar.getInstance();
				c.setTime(d);
				c.add(Calendar.MONTH, 1);
				c.add(Calendar.SECOND, -1);
				String endTime = sf1.format(c.getTime());
				c.setTime(d);
				String begtinTime = sf1.format(c.getTime());
				c.setTime(d);
				c.add(Calendar.MONTH, -1);
				d = c.getTime();
				time[i][0] = begtinTime;
				time[i][1] = endTime;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 指定开始日期和结束日期，分割日期
	 *
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param num
	 *            分割日期的段数
	 * @return 分割后的日期，List<Map<String, String>> 每个Map代表一个分割后的日期段
	 * @throws Exception
	 */
	public List<Map<String, String>> getSplitTime(String beginDate, String endDate, int num) throws Exception {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<String> dayList = getDayList(beginDate, endDate);
		String preDate = null;
		String curDate = null;
		int split = dayList.size() / num;
		split = split == 0 ? 1 : split;
		for (int i = 0; i < dayList.size(); i = i + split) {
			Map<String, String> map = new HashMap<String, String>();
			if (i == 0) {
				preDate = dayList.get(i);
			} else {
				curDate = dayList.get(i);
				if (i == split) {
					map.put("beginDate", preDate);
					map.put("endDate", curDate);
					if (split == 1) {
						map.put("beginDate", preDate);
						map.put("endDate", preDate);
						result.add(map);
						map = new HashMap<String, String>();
						map.put("beginDate", curDate);
						map.put("endDate", curDate);
						result.add(map);
					}
					result.add(map);
				} else {
					if (i + split >= dayList.size()) {
						map.put("beginDate", getTime(preDate, 1));
						map.put("endDate", dayList.get(dayList.size() - 1));
					} else {
						map.put("beginDate", getTime(preDate, 1));
						map.put("endDate", curDate);
					}
					result.add(map);
				}
				preDate = curDate;
			}
		}
		return result;
	}


	/**
	 * 按月份分割时间并以-连接
	 * 比如begin：2014-04-19 end：2014-11-30
	 * 分割得到04.19-04.30...11.01-11.30
	 * @param begin
	 * 				开始时间
	 * @param end
	 * 				结束时间
	 * @return
	 */
	public static List<String> getDateInterval(Date begin, Date end) {
		List<String> list = new ArrayList<String>();
		// 开始日期不能大于结束日期
		if (!begin.before(end)) {
			return null;
		}

		Calendar cal_begin = Calendar.getInstance();
		cal_begin.setTime(begin);

		Calendar cal_end = Calendar.getInstance();
		cal_end.setTime(end);
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");


		while (true) {
			StringBuffer strbuf = new StringBuffer();
			if (cal_begin.get(Calendar.YEAR) == cal_end.get(Calendar.YEAR)
					&& cal_begin.get(Calendar.MONTH) == cal_end
							.get(Calendar.MONTH)) {
				strbuf.append(sdf.format(cal_begin.getTime())).append("-")
						.append(sdf.format(cal_end.getTime()));
				list.add(strbuf.toString());
				break;
			}
			String str_begin = sdf.format(cal_begin.getTime());
			String str_end = getMonthEnd(cal_begin.getTime());
			strbuf.append(str_begin).append("-").append(str_end);
			cal_begin.add(Calendar.MONTH, 1);
			cal_begin.set(Calendar.DAY_OF_MONTH, 1);
			// String str_end =;
			list.add(strbuf.toString());
		}
		Collections.reverse(list);
		return list;

	}

	/**
	 * 取得指定月份的最后一天
	 *
	 * @param strdate
	 *            String
	 * @return String
	 */
	public static String getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDateByFormat(calendar.getTime(), "MM.dd");
	}
	/**
	 * 取得指定月份的最后一天
	 *
	 * @param String
	 * @return String
	 * @throws ParseException 
	 */
	public static String getMonthEnd2(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDateByFormat(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public String[] getWeek(String date){
		String[] week = new String[2];
		try {
			Date d = this.formatDate(date, "yyyy-MM-dd");
			if(d.getDay() == 0){
				List<String> list = this.getDayList(date, -7);
				week[0] = list.get(list.size()-1);
				week[1] = list.get(0);
				return week;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得指定日期往前后N天的日期列表
	 *
	 * @param date
	 *            指定日期
	 * @param num
	 *            天数
	 * @return
	 */
	public List<String> getDayList(String date, int num) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar c = Calendar.getInstance();
			int add = 1;
			if (num < 0) {
				num = -num;
				add = -1;
			}
			c.setTime(sf.parse(date));
			for (int i = 0; i < num; i++) {
				list.add(sf.format(c.getTime()));
				c.add(Calendar.DATE, add);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 得到开始日期和结束日期
	 *
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 日期列表
	 */
	public List<String> getDayList(String beginDate, String endDate) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date end = sf.parse(endDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sf.parse(beginDate));
			while (c.getTime().before(end)) {
				list.add(sf.format(c.getTime()));
				c.add(Calendar.DATE, 1);
			}
			list.add(sf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 得到开始日期和结束日期
	 *
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 日期列表
	 */
	public List<String> getDayList(String beginDate, String endDate,String format) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			Date end = sf.parse(endDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sf.parse(beginDate));
			while (c.getTime().before(end)) {
				list.add(sf.format(c.getTime()));
				c.add(Calendar.DATE, 1);
			}
			list.add(sf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 得到开始日期和结束日期（按照星期几排序）
	 *
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 日期列表
	 */
	public List<String> getDayListOrderWeekDay(String beginDate, String endDate) {
		List<String> result = new ArrayList<String>();
		Map<Integer, String> dateMap = new HashMap<Integer, String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date end = sf.parse(endDate);
			Calendar c = Calendar.getInstance();
			c.setTime(sf.parse(beginDate));
			while (c.getTime().before(end)) {
				dateMap.put(c.get(Calendar.DAY_OF_WEEK), sf.format(c.getTime()));
				c.add(Calendar.DATE, 1);
			}
			dateMap.put(c.get(Calendar.DAY_OF_WEEK), sf.format(c.getTime()));
			result.add(dateMap.get(Calendar.MONDAY));
			result.add(dateMap.get(Calendar.TUESDAY));
			result.add(dateMap.get(Calendar.WEDNESDAY));
			result.add(dateMap.get(Calendar.THURSDAY));
			result.add(dateMap.get(Calendar.FRIDAY));
			result.add(dateMap.get(Calendar.SATURDAY));
			result.add(dateMap.get(Calendar.SUNDAY));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 指定年和月，加上月份数的月列表
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param relativeRadioNum
	 *            月份数
	 * @return 月列表 yyyy-MM
	 */
	public List<String> getMonthList(String year, String month, String relativeRadioNum) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM");
		DecimalFormat df = new DecimalFormat("00");
		try {
			Date d = sf.parse(year + df.format(Integer.valueOf(month)));
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			for (int i = 0; i < Integer.parseInt(relativeRadioNum) + 1; i++) {
				list.add(sf1.format(c.getTime()));
				c.add(Calendar.MONTH, -1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 设置处理日期.
	 *
	 * @param n天前
	 * @return 日期
	 */
	public Date getDate(int n) {
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(c.DATE, -n);
		Date time = c.getTime();
		return time;
	}

	/**
	 * 指定两天相差的天数
	 *
	 * @param date1
	 *            开始日期
	 * @param date2
	 *            结束日期
	 * @return 两天相差的天数
	 */
	public int getDateDiff(String date1, String date2) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		int diff = 0;
		try {
			diff = (int) ((sf.parse(date2).getTime() - sf.parse(date1).getTime()) / 1000 / 3600 / 24) + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}

	/**
	 * 格式化指定日期
	 *
	 * @param date
	 *            日期
	 * @param srcFormat
	 *            当前日期格式
	 * @param targetFormat
	 *            要转换的日期格式
	 * @return 格式化的指定日期
	 * @throws Exception
	 */
	public String formatDate(String date, String srcFormat, String targetFormat) throws Exception {
		SimpleDateFormat src = new SimpleDateFormat(srcFormat);
		SimpleDateFormat target = new SimpleDateFormat(targetFormat);
		return target.format(src.parse(date));
	}

	/**
	 * 格式化指定日期
	 *
	 * @param date
	 *            日期
	 * @param targetFormat
	 *            要转换的日期格式
	 * @return 格式化的指定日期
	 * @throws Exception
	 */
	public String formatDate(Date date, String targetFormat) throws Exception {
		SimpleDateFormat target = new SimpleDateFormat(targetFormat);
		return target.format(date);
	}

	/**
	 * 转换为日期
	 *
	 * @param date
	 *            日期
	 * @param srcFormat
	 *            当前日期格式
	 * @return 转换的日期
	 * @throws Exception
	 */
	public Date formatDate(String date, String srcFormat) throws Exception {
		SimpleDateFormat src = new SimpleDateFormat(srcFormat);
		return src.parse(date);
	}

	/**
	 * 得到指定日期的月的第一天
	 *
	 * @param date
	 *            指定日期
	 * @return 当前日期的月的第一天
	 * @throws Exception
	 */
	public String getFirstDayOnMonth(String date) throws Exception {
		return formatDate(date, "yyyy-MM-dd", "yyyy-MM-01");
	}

	/**
	 * 得到当前日期的月的第一天
	 *
	 * @return 当前日期的月的第一天
	 * @throws Exception
	 */
	public String getFirstDayOnCurMonth() throws Exception {
		return getFristDayOfMonth(new Date(), 0);
	}

	/**
	 * 得到当天
	 *
	 * @return 当天yyyy-MM-dd
	 * @throws Exception
	 */
	public String getCurDayOnCurMonth() throws Exception {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	public String addSecondTime(String time , long seconds) throws Exception {
		Date date = formatDate(time, "yyyyMMddHHmmss");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, (int)seconds);
		return formatDate(c.getTime(), "yyyyMMddHHmmss");
	}
	/**
	 * 时间加上秒
	 * @param time
	 * @param srcFormat
	 * @param destFormat
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public String addSecondTime(String time , String srcFormat , String destFormat, long seconds) throws Exception {
		Date date = formatDate(time, srcFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, (int)seconds);
		return formatDate(c.getTime(), destFormat);
	}

	public String getProcessYear(){
		return "2014";
	}

	/**
	 * 根据开始时间和结束时间得到CSM的开始时间结束时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public String[] getCSMTime(String beginTime,String endTime) throws Exception {
		String[] result = new String[3];
		String pre=beginTime.substring(0, 12);
		String next=endTime.substring(0, 12);

		if(pre.equals(next)){
			if(Integer.parseInt(endTime.substring(12, 14))-Integer.parseInt(beginTime.substring(12, 14))>30){
				result[0] = beginTime.substring(0, 12)+"00";
				result[1] = beginTime.substring(0, 12)+"00";
				result[2] = "1";
				result[0] = formatDate(result[0],"yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss");
				result[1] = formatDate(result[1],"yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss");
			}
		}else{
			if (Integer.parseInt(beginTime.substring(12, 14)) <= 30) {
				result[0] = beginTime.substring(0, 12)+"00";
			} else {
				result[0] = this.addMinute(beginTime, "yyyyMMddHHmmss", 1).substring(0, 12)+"00";
			}

			if (Integer.parseInt(endTime.substring(12, 14)) > 30) {
				result[1] = endTime.substring(0, 12)+"00";
			} else {
				result[1] = this.addMinute(endTime, "yyyyMMddHHmmss", -1).substring(0, 12)+"00";
			}
			result[2] = String.valueOf((formatDate(result[1],"yyyyMMddHHmmss").getTime() - formatDate(result[0],"yyyyMMddHHmmss").getTime())/1000/60+1);
			result[0] = formatDate(result[0],"yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss");
			result[1] = formatDate(result[1],"yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss");
		}
		return result;
	}

	/**
	 * 时间间隔
	 * @param beginTime
	 * @param endTime
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public Long getTimeLength(String beginTime,String endTime,String format) throws Exception {
		return (formatDate(endTime, format).getTime() - formatDate(beginTime, format).getTime())/1000;
	}


	/**
	 * <p>HH:mm:ss 格式相加后，返回 time</p>
	 * @param beginTime  yyyy-MM-dd HH:mm:ss
	 * @param HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws Exception
	 */
	public String getTimeAddHHmmss (String beginTime, String HHmmss) throws Exception {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
		Date time = sf.parse(beginTime);

		time.setSeconds(time.getSeconds() + Integer.valueOf(HHmmss.split(":")[2]));
		time.setMinutes(time.getMinutes() + Integer.valueOf(HHmmss.split(":")[1]));
		time.setHours(time.getHours() + Integer.valueOf(HHmmss.split(":")[0]));


		return sf.format(time);
	}

	/*public static void main(String[] args) throws Exception{
		String minuteBetweenTime = new DateUtil().getMinuteBetweenTime("2015-11-11 11:12:34", "21:11:34");
		System.out.println(minuteBetweenTime);
	}*/

	public List<String> getMonthDateList(String begindate, String enddate) {
		List<String> datelist = new ArrayList<String>();
		int beginyear = Integer.parseInt(begindate.split("-")[0].toString());
		int beginmonth = Integer.parseInt(begindate.split("-")[1].toString());
		int endyear = Integer.parseInt(enddate.split("-")[0].toString());
		int endmonth = Integer.parseInt(enddate.split("-")[1].toString());
		for(int i = beginyear;i<= endyear;i++) {
			int year = i;
			int firstmonth = 1;
			int lastmonth = 12;
			if(year == beginyear) {
				firstmonth = beginmonth;
			}
			if(year == endyear) {
				lastmonth = endmonth;
			}
			for(int j = firstmonth;j <= lastmonth;j++) {
				datelist.add(year+"-"+(j<10?"0"+j:j));
			}
		}
		return datelist;
	}

	public static int dayOfWeek(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String replaceAll = day.replaceAll("-", "");
		String strDate = replaceAll.substring(0, 8);
		int dayForWeek = 0;
		try {
			Date date = sdf.parse(strDate);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 判断时间是否在开始时间和结束时间之间,主要用于读文件时对比时间范围
	 *
	 * @param time
	 *            20141219121212
	 * @param begintime
	 *            20141219121212
	 * @param endtime
	 *            20141219121212
	 * @return
	 * @author wangsc
	 */
	public static boolean isTimeBetweenBeginAndEnd(String time,
			String begintime, String endtime) {
		int cp1 = time.compareTo(begintime);
		int cp2 = time.compareTo(endtime);
		if (cp1 >= 0 && cp2 <= 0) {
			return true;
		}
		return false;
	}
	/**
	 * 将时间分钟化.
	 * @param date 时间.
	 * @return
	 */
	public static String formatDateByFormat(String date) {
		String format = "yyyy-MM-dd HH:mm";
		String result = "";
		if (date != "") {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date time = sdf.parse(date);
				result = sdf.format(time)+":00";
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取指定日期所在周的指定日
	 *
	 * @param dateStr 需要转换的日期(格式：yyyy-MM-dd)
	 * @param day 转换的目标日(1~7对应星期一~星期日)
	 * @return
	 * @throws Exception
	 */
	public static String getDayofWeek(String dateStr, int day) throws Exception{
		if (day == 7) {
			day = 8;
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sf.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, day + 1);
		return sf.format(cal.getTime());
	}
}

