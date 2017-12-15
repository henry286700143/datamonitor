package com.viewstar.jsdatamonitor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ʱ�䴦��.
 * @author long
 *
 */
public class TimeUtil {

	/**
	 * �°汨���ڲ��ô˷���
	 * �������º�����.
	 * �������ǵ�ǰ�£�endTimeʱ��Ϊ��ǰʱ���ǰһ��?.
	 * ����Լ���ǵ�ǰ�£�endTimeʱ��Ϊ���µ����һ��?.
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Map<String, String> getTime(String year, String month, String num) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = "";
		String endTime = "";
		String endYear = year;
		String endMonth = month;

		String MonthBeginTime = "";
		String thisEndTime = "";
		String thisBeginTime = "";
		int intmonth = Integer.valueOf(month);
		int intnum = Integer.valueOf(num);
		// �����ж�.
		if (intmonth + 1 - intnum <= 0) {
			year = String.valueOf((Integer.valueOf(year) - 1));
			month = String.valueOf(intmonth - intnum + 13);
		} else {
			month = String.valueOf(intmonth - intnum + 1);
		}
		// ��ȡ���һ��c.
		MonthBeginTime = year + "-" + month + "-01";
		Calendar c = Calendar.getInstance();
		int dateOfMonth = 1;

		if (c.getTime().getMonth() + 1 != Integer.valueOf(endMonth)) {
			c.set(Calendar.YEAR, Integer.valueOf(endYear));
			c.set(Calendar.MONTH, Integer.valueOf(endMonth) - 1);
			dateOfMonth = c.getActualMaximum(Calendar.DATE);
			thisEndTime = endYear + "-" + endMonth + "-" + dateOfMonth;
		} else {
			c.set(Calendar.YEAR, Integer.valueOf(endYear));
			c.set(Calendar.MONTH, Integer.valueOf(endMonth) - 1);
			// �˴��������������¿����ӳ�.
			java.util.Date time = c.getTime();
			time.setTime(time.getTime() - 1000*60*60*24*2);
			thisEndTime = sdf.format(time);
		}
		thisBeginTime = endYear + "-" + endMonth + "-" + "01";
		map.put("thisBeginTime", thisBeginTime);
		map.put("thisEndTime", thisEndTime);
		map.put("MonthBeginTime", MonthBeginTime);
		return map;
	}

	/**
	 * ---------�°汨����ô˷���?-----------
	 * �������º�����.
	 * �������ǵ�ǰ�£�endTimeʱ��Ϊ��ǰʱ��.
	 * �����²��ǵ�ǰ�£�endTimeʱ��Ϊ���µ����һ��?.
	 * @param beginTime  ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @param num ����������2��ʾ��ǰһ����
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, String> getTimeDayBeginEnd (String year, String month, String num) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = "";
		String endTime = "";
		String endYear = year;
		String endMonth = month;

		String MonthBeginTime = "";
		String thisEndTime = "";
		String thisBeginTime = "";
		int intmonth = Integer.valueOf(month);
		int intnum = Integer.valueOf(num);
		// �����ж�.
		if (intmonth + 1 - intnum <= 0) {
			year = String.valueOf((Integer.valueOf(year) - 1));
			month = String.valueOf(intmonth - intnum + 13);
		} else {
			month = String.valueOf(intmonth - intnum + 1);
		}
		// ��ȡ���һ��c.
		MonthBeginTime = year + "-" + month + "-01";
		Calendar c = Calendar.getInstance();
		int dateOfMonth = 1;

		if (c.getTime().getMonth() + 1 != Integer.valueOf(endMonth) && c.getTime().getYear() != Integer.valueOf(endYear)) {
			c.set(Calendar.YEAR, Integer.valueOf(endYear));
			c.set(Calendar.MONTH, Integer.valueOf(endMonth) - 1);
			dateOfMonth = c.getActualMaximum(Calendar.DATE);
			thisEndTime = endYear + "-" + endMonth + "-" + dateOfMonth;
		} else {
			c.set(Calendar.YEAR, Integer.valueOf(endYear));
			c.set(Calendar.MONTH, Integer.valueOf(endMonth) - 1);
			java.util.Date time = c.getTime();
			time.setTime(time.getTime());
			thisEndTime = sdf.format(time);
		}
		thisBeginTime = endYear + "-" + endMonth + "-" + "01";
		map.put("thisBeginTime", thisBeginTime);
		try {
			map.put("thisEndTime", getEndDateOfMonth(thisBeginTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("MonthBeginTime", MonthBeginTime);
		return map;
	}
	
	 /**
	  * ��ȡһ���µ����һ��?
	  * 
	  * @param date  yyyy-MM-dd
	  * @return
	 * @throws ParseException 
	  */
	public static String getEndDateOfMonth(String date) throws ParseException {// yyyy-MM-dd
		String str = date.split("-")[0] + "-" + date.split("-")[1] + "-";
		String month = date.split("-")[1];
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(date)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}
	
	/**
	 * �ж��Ƿ�����
	 * 
	 * @param ddate
	 * @return
	 * @throws ParseException 
	 */
	public static boolean isLeapYear(String ddate) throws ParseException {

		/**
		 * ��ϸ��ƣ�? 1.��400���������꣬���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
		 * 3.�ܱ�4����ͬʱ�ܱ�100������������
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	

	/**
	 * �°汨���ٲ��ô˷���
	 * ��ȡÿ���µ����һ��?.
	 * ����ǰ����Ϊ���죬�������һ��Ϊ��ǰ����?.
	 * ����ǰ���ڲ��ǽ��죬���ȡ���µ����һ��.
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<String> getThisMonthLastDay (String year, String month, String num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();

		Map<String, String> map = this.getTime(year, month, num);
		String beginTime = map.get("MonthBeginTime");
		String endTime = map.get("thisEndTime");

		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, Integer.valueOf(endTime.split("-")[0]));
		end.set(Calendar.MONTH, Integer.valueOf(endTime.split("-")[1]) - 1);
		end.set(Calendar.DATE, Integer.valueOf(endTime.split("-")[2]));
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.YEAR, Integer.valueOf(beginTime.split("-")[0]));
		begin.set(Calendar.MONTH, Integer.valueOf(beginTime.split("-")[1]) - 1);
		begin.set(Calendar.DATE, Integer.valueOf(beginTime.split("-")[2]));

		while (end.after(begin)) {
			list.add(sdf.format(end.getTime()));
			end.set(end.DAY_OF_MONTH, 0);
		}

		return list;
	}

	/**
	 * --------------�°汨����ô˷���?--------------
	 * ��ȡÿ���µ����һ��?.
	 * ����ǰ����Ϊ���죬�������һ��Ϊ��ǰ����?.
	 * ����ǰ���ڲ��ǽ��죬���ȡ���µ����һ��.
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException 
	 */
	public List<String> getMonthLastDay (String year, String month, String num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();

		Map<String, String> map = this.getTimeDayBeginEnd(year, month, num);
		String beginTime = map.get("MonthBeginTime");
		String endTime = map.get("thisEndTime");

		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, Integer.valueOf(endTime.split("-")[0]));
		end.set(Calendar.MONTH, Integer.valueOf(endTime.split("-")[1]) - 1);
		end.set(Calendar.DATE, Integer.valueOf(endTime.split("-")[2]));
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.YEAR, Integer.valueOf(beginTime.split("-")[0]));
		begin.set(Calendar.MONTH, Integer.valueOf(beginTime.split("-")[1]) - 1);
		begin.set(Calendar.DATE, Integer.valueOf(beginTime.split("-")[2]));

		while (end.after(begin)) {
			list.add(sdf.format(end.getTime()));
			end.set(end.DAY_OF_MONTH, 0);
		}

		return list;
	}
	/**
	 * ��ʼ����ʱ��.
	 * @param beginTime
	 * @param endTime
	 * @return ���ظ�ʱ�䷶Χ�ڵ�����ʱ��list
	 */
	public List<String> getBeginToEndDayTime(String beginTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		begin.set(Integer.valueOf(beginTime.split("-")[0]), Integer.valueOf(beginTime.split("-")[1]) - 1, Integer.valueOf(beginTime.split("-")[2]) - 1);
		end.set(Integer.valueOf(endTime.split("-")[0]), Integer.valueOf(endTime.split("-")[1]) - 1, Integer.valueOf(endTime.split("-")[2]));
		while (end.after(begin)) {
			list.add(sdf.format(end.getTime()));
			Date time = end.getTime();
			time.setTime(time.getTime() - 1000*60*60*24);
			end.setTime(time);
		}
		Collections.reverse(list);
		return list;
	}
	/**
	 * ����ʱ����?2013-09-09���ó����µ�һ��.
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public String getMonthFirstDay (String date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		time.setTime(sdf.parse(date));
		time.set(Calendar.DATE, 1);
		return sdf.format(time.getTime());
	}

	public String getMonthLastDay (String date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		time.setTime(sdf.parse(date));
		time.set(Calendar.DAY_OF_MONTH, time.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(time.getTime());
	}

	/**
	 * ����������ͬ�ڶԱ�
	 * ������ǰ����2014-05-06
	 * ��������2014-05-31
	 * �õ�map preFirstDay 2014-05-01
	 * preLastDay 2014-05-06
	 * FirstDay 2014-04-01
	 * LastDay 2014-04-06
	 * ֧�������·�ʱ�䳤�Ȳ�һ��
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getLastMonthSameDay (String date) throws Exception{

		Map<String,String> map = new HashMap<String, String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date thisTime = new Date();
		String now = sdf.format(thisTime);

		String nowTime = now.split("-")[1];
		String preDate = date.split("-")[1];
		
		String nowYear = nowTime.split("-")[0];
		String preYear = date.split("-")[0];

		if (nowTime.equals(preDate) && nowYear.equals(preYear)) {

			String day = now.split("-")[2];
			TimeUtil timeUtil = new TimeUtil();

			List<String> thisMonthLastDay = timeUtil.getThisMonthLastDay(now.split("-")[0], now.split("-")[1], "2");
			String lastMonthDay = thisMonthLastDay.get(1);

			String lastDay = lastMonthDay.split("-")[2];

			String FirstDay = "";
			String LastDay = "";

			if (Integer.valueOf(day) > Integer.valueOf(lastDay)) {
				LastDay = lastMonthDay;
				FirstDay = timeUtil.getMonthFirstDay(LastDay);
				String preFirstDay = timeUtil.getMonthFirstDay(now);
				map.put("preFirstDay", preFirstDay);
				map.put("preLastDay", now);
				map.put("FirstDay", FirstDay);
				map.put("LastDay", LastDay);
			} else {
				LastDay = lastMonthDay;
				FirstDay = timeUtil.getMonthFirstDay(LastDay);
				String preFirstDay = timeUtil.getMonthFirstDay(now);
				map.put("preFirstDay", preFirstDay);
				map.put("preLastDay", now);
				map.put("FirstDay", FirstDay);
				map.put("LastDay", LastDay.substring(0, 8) + day);
			}

		} else {
			TimeUtil timeUtil = new TimeUtil();
			String monthFirstDay = timeUtil.getMonthFirstDay(date);
			String monthLastDay = timeUtil.getMonthLastDay(date);

			map.put("preFirstDay", monthFirstDay);
			map.put("preLastDay", monthLastDay);

			List<String> thisMonthLastDay = timeUtil.getThisMonthLastDay(monthFirstDay.split("-")[0], monthFirstDay.split("-")[1], "2");
			String day = thisMonthLastDay.get(1);

			String monthFirstDay2 = timeUtil.getMonthFirstDay(day);
			String monthLastDay2 = timeUtil.getMonthLastDay(day);

			map.put("FirstDay", monthFirstDay2);
			map.put("LastDay", monthLastDay2);
		}
		return map;
	}

	public Map<String, String> getLastMonthSameDay (String beginTime, String endTime) throws Exception{

		DateUtil util = new DateUtil();
		String timeMonth = util.getTimeMonth(beginTime, -1);
		System.out.println(timeMonth);

		/*Map<String,String> map = new HashMap<String, String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date thisTime = new Date();
		String now = sdf.format(thisTime);

		String nowTime = now.split("-")[1];
		String preDate = date.split("-")[1];

		if (nowTime.equals(preDate)) {

			String day = now.split("-")[2];
			TimeUtil timeUtil = new TimeUtil();

			List<String> thisMonthLastDay = timeUtil.getThisMonthLastDay(now.split("-")[0], now.split("-")[1], "2");
			String lastMonthDay = thisMonthLastDay.get(1);

			String lastDay = lastMonthDay.split("-")[2];

			String FirstDay = "";
			String LastDay = "";

			if (Integer.valueOf(day) > Integer.valueOf(lastDay)) {
				LastDay = lastMonthDay;
				FirstDay = timeUtil.getMonthFirstDay(LastDay);
				String preFirstDay = timeUtil.getMonthFirstDay(now);
				map.put("preFirstDay", preFirstDay);
				map.put("preLastDay", now);
				map.put("FirstDay", FirstDay);
				map.put("LastDay", LastDay);
			} else {
				LastDay = lastMonthDay;
				FirstDay = timeUtil.getMonthFirstDay(LastDay);
				String preFirstDay = timeUtil.getMonthFirstDay(now);
				map.put("preFirstDay", preFirstDay);
				map.put("preLastDay", now);
				map.put("FirstDay", FirstDay);
				map.put("LastDay", LastDay.substring(0, 8) + day);
			}

		} else {
			TimeUtil timeUtil = new TimeUtil();
			String monthFirstDay = timeUtil.getMonthFirstDay(date);
			String monthLastDay = timeUtil.getMonthLastDay(date);

			map.put("preFirstDay", monthFirstDay);
			map.put("preLastDay", monthLastDay);

			List<String> thisMonthLastDay = timeUtil.getThisMonthLastDay(monthFirstDay.split("-")[0], monthFirstDay.split("-")[1], "2");
			String day = thisMonthLastDay.get(1);

			String monthFirstDay2 = timeUtil.getMonthFirstDay(day);
			String monthLastDay2 = timeUtil.getMonthLastDay(day);

			map.put("FirstDay", monthFirstDay2);
			map.put("LastDay", monthLastDay2);
		}*/
		return null;
	}

	/**
	 * ȡ��ĳһ�·ݵ�����
	 * @param yearMonth
	 * @return
	 * @author wangsc
	 */
	public int getMonthDays(String yearMonth) {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM"); //���д�������յ���ʽ�Ļ���ҪдСd���磺"yyyy-MM-dd"
		try {
			rightNow.setTime(simpleDate.parse(yearMonth)); //Ҫ��������Ҫ���·ݣ��ı����Ｔ��
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}
	
	/**
	 * �������ڣ��������ڼ�
	 * @param time ��ʽ yyyy-MM-dd
	 * @return
	 * @throws Exception
	 */
	public String getWeekDay(String time) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(time));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return String.valueOf(dayForWeek);
	}


	/**
	 * ��ȡ�ϸ��µĵ�һ������ yyyy-mm-dd
	 * @param time ʱ����? yyyy-mm-dd
	 * @return
	 */
	public String getLashMonthFirstDay (String time) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date curDate = sdf.parse(time);
		calendar.setTime(curDate);
		//ȡ����һ��ʱ��
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
		
		String monthFirstDay = getMonthFirstDay(sdf.format(calendar.getTime()));
		
		return monthFirstDay;
	}
	
	/**
	 * ��ȡ�ϸ��µ����һ��? yyyy-mm-dd
	 * @param time yyyy-mm-dd
	 * @return
	 * @throws Exception
	 */
	public String getLastMonthLastDay (String time) throws Exception  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date curDate = sdf.parse(time);
		calendar.setTime(curDate);
		//ȡ����һ��ʱ��
		calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);
		String monthLastDay = getMonthLastDay(sdf.format(calendar.getTime()));
		return monthLastDay;
	}
	
	
	public static void main(String[] args) throws Exception{
		String lastMonthLastDay = new TimeUtil().getLastMonthLastDay("2015-01-04");
		System.out.println(lastMonthLastDay);
	}

}
