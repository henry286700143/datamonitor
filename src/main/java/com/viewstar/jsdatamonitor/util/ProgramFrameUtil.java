package com.viewstar.jsdatamonitor.util;

import java.util.ArrayList;
import java.util.List;

public class ProgramFrameUtil {

	private String beginDate;

	private String endDate;

	private List<String> weekDayList;

	private String[] weekName = new String[]{"��һ","�ܶ�","����","����","����", "����","����"};

	private DateUtil dataUtil = new DateUtil();

	public ProgramFrameUtil(String beginDate, String endDate) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		weekDayList = dataUtil.getDayList(beginDate, endDate);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
	}
/*
	public static void channel1() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-08-17","2015-08-23");
		ProgramFrameUtil.frameSql("8","���Ӿ糡", "���Ӿ�", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0000:1-7)",0);
		ProgramFrameUtil.frameSql("1","��ɫ����ڶ���?", "���ƽ�Ŀ", new String[]{"20:00-23:59"}, new int[]{6},"RESULT=MAX(2000:6)",0);
		ProgramFrameUtil.frameSql("1","����Ī��", "���ƽ�Ŀ", new String[]{"20:00-23:59"}, new int[]{7,1},"RESULT=AVG(2000:1,2000:7)",0);
		ProgramFrameUtil.frameSql("1","�������?", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{4},"RESULT=MAX(2000:4)",0);
		ProgramFrameUtil.frameSql("1","ȺӢ��", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{3},"RESULT=MAX(2000:3)",0);
		ProgramFrameUtil.frameSql("1","�����Ի�", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{2},"RESULT=MAX(2000:2)",0);
		ProgramFrameUtil.frameSql("1","������Ե", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{5},"RESULT=MAX(2000:5)",0);

		ProgramFrameUtil.frameSql("1","�������?", "���Ž�Ŀ", new String[]{"17:30-20:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1730:1-7)",0);
		ProgramFrameUtil.frameSql("1","�򳿲���", "���Ž�Ŀ", new String[]{"06:00-09:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0600:1-7)",0);
		ProgramFrameUtil.frameSql("1","�������?", "���Ž�Ŀ", new String[]{"22:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2200:1-7)",0);
		ProgramFrameUtil.frameSql("1","12�㱨��", "���Ž�Ŀ", new String[]{"11:00-13:30"}, new int[]{1,2,3,4,5,6},"RESULT=AVG(1100:1-6)",0);
		ProgramFrameUtil.frameSql("1","��ʵ", "���Ž�Ŀ", new String[]{"11:00-13:30"}, new int[]{7},"RESULT=MAX(1100:7)",0);

		ProgramFrameUtil.frameSql("1","����Ԥ��", "�⹺��������Ŀ", new String[]{"18:00-20:00","22:30-23:59"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(1800:1-7);AVG2=AVG(2230:1-7);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("1","ת������̨��������", "�⹺��������Ŀ", new String[]{"18:00-20:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1800:1-7)",0);
		ProgramFrameUtil.frameSql("1","���ļ���", "�⹺��������Ŀ", new String[]{"07:00-09:30"}, new int[]{6},"RESULT=MAX(0700:6)",0);
		ProgramFrameUtil.frameSql("1","�����й�", "�⹺��������Ŀ", new String[]{"17:00-19:30","22:30-23:59"}, new int[]{7},"RESULT=MAX(1700:7,2230:7)",0);
		ProgramFrameUtil.frameSql("1","���ʵ����?", "�⹺��������Ŀ", new String[]{"07:00-09:30"}, new int[]{7},"RESULT=MAX(0700:7)",0);
		ProgramFrameUtil.frameSql("1","HELLO���?", "�⹺��������Ŀ", new String[]{"22:30-23:59"}, new int[]{1,2,3,4},"RESULT=AVG(2230:1-4)",0);
		ProgramFrameUtil.frameSql("1","����", "�⹺��������Ŀ", new String[]{"17:00-19:30"}, new int[]{1,2,3,4,5},"RESULT=AVG(1700:1-5)",0);
		ProgramFrameUtil.frameSql("1","���Ϲ����ж�", "�⹺��������Ŀ", new String[]{"17:00-19:30"}, new int[]{1,2,3,4,5},"RESULT=AVG(1700:1-5)",0);
		ProgramFrameUtil.frameSql("1","����WOMEN", "�⹺��������Ŀ", new String[]{"17:00-19:30","22:30-23:59"}, new int[]{6},"RESULT=AVG(1700:6,2230:6)",0);
		ProgramFrameUtil.frameSql("1","�ҵ��ھ��ǿ���", "�⹺��������Ŀ", new String[]{"07:00-09:30"}, new int[]{1,2,3,4,5},"RESULT=AVG(0700:1-5)",0);

		ProgramFrameUtil.frameSql("1","���������?", "���Ӿ�", new String[]{"19:00-21:15"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1900:1-7)",0);
		ProgramFrameUtil.frameSql("1","��о�?", "���Ӿ�", new String[]{"12:00-17:50"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1200:1-7)",0);
		ProgramFrameUtil.frameSql("1","���о糡", "���Ӿ�", new String[]{"08:00-11:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0800:1-7)",0);
		ProgramFrameUtil.frameSql("1","ͨ���糡1", "���Ӿ�", new String[]{"23:00-03:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2300:1-7)",1);
		ProgramFrameUtil.frameSql("1","ͨ���糡2", "���Ӿ�", new String[]{"03:30-05:40"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0330:1-7)",0);
	}
	public static void channel2() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("2","���б���60��", "���ƽ�Ŀ", new String[]{"17:30-20:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1730:1-7)",0);
		ProgramFrameUtil.frameSql("2","��1�����?", "���ƽ�Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("2","�Ȳ�1Сʱ", "���ƽ�Ŀ", new String[]{"16:30-19:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1630:1-7)",0);
		ProgramFrameUtil.frameSql("2","��1�۲�", "���ƽ�Ŀ", new String[]{"18:30-20:30"}, new int[]{1,2,3,4,5,7},"RESULT=AVG(1830:1-5,1830:7)",0);
		ProgramFrameUtil.frameSql("2","�����������?", "���ƽ�Ŀ", new String[]{"18:30-20:30"}, new int[]{6},"RESULT=MAX(1830:6)",0);
		ProgramFrameUtil.frameSql("2","�����綯Ա", "���ƽ�Ŀ", new String[]{"07:00-11:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0700:1-7)",0);
		ProgramFrameUtil.frameSql("2","�����ӳ���", "���ƽ�Ŀ", new String[]{"21:00-22:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2100:1-7)",0);
		ProgramFrameUtil.frameSql("2","���Ŀ�", "���ƽ�Ŀ", new String[]{"21:30-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2130:1-7)",0);
		ProgramFrameUtil.frameSql("2","��������", "���ƽ�Ŀ", new String[]{"20:30-23:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(2030:1-5)",0);
		ProgramFrameUtil.frameSql("2","����1��", "���ƽ�Ŀ", new String[]{"19:30-22:00"}, new int[]{7},"RESULT=MAX(1930:7)",0);
		ProgramFrameUtil.frameSql("2","����̸", "���ƽ�Ŀ", new String[]{"19:30-22:00"}, new int[]{1,2,3,4,5,6},"RESULT=AVG(1930:1-6)",0);
		ProgramFrameUtil.frameSql("2","������1��", "���ƽ�Ŀ", new String[]{"20:30-22:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2030:1-7)",0);

		ProgramFrameUtil.frameSql("2","������", "�⹺��������Ŀ", new String[]{"18:30-20:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1830:1-7)",0);
		ProgramFrameUtil.frameSql("2","12�㱨��", "�⹺��������Ŀ", new String[]{"11:00-13:30"}, new int[]{1,2,3,4,5,6},"RESULT=AVG(1100:1-6)",0);
		ProgramFrameUtil.frameSql("2","���󻷾�Ԥ��", "�⹺��������Ŀ", new String[]{"19:00-21:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1900:1-7)",0);
		ProgramFrameUtil.frameSql("2","�򳿲���", "�⹺��������Ŀ", new String[]{"06:00-09:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0600:1-7)",0);
		ProgramFrameUtil.frameSql("2","�������?", "�⹺��������Ŀ", new String[]{"19:30-21:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1930:1-7)",0);
		ProgramFrameUtil.frameSql("2","ϲ���콵", "�⹺��������Ŀ", new String[]{"19:00-21:00","20:30-22:30"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(1900:1-7);AVG2=AVG(2030:1-7);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("2","����Ԥ��", "�⹺��������Ŀ", new String[]{"19:30-21:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1930:1-7)",0);
		ProgramFrameUtil.frameSql("2","��������", "�⹺��������Ŀ", new String[]{"20:30-23:00"}, new int[]{6},"RESULT=MAX(2030:6)",0);
		ProgramFrameUtil.frameSql("2","��ʵ", "�⹺��������Ŀ", new String[]{"11:00-13:30"}, new int[]{7},"RESULT=MAX(1100:7)",0);
		ProgramFrameUtil.frameSql("2","�������?", "�⹺��������Ŀ", new String[]{"22:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2200:1-7)",0);
		ProgramFrameUtil.frameSql("2","ʱ������", "�⹺��������Ŀ", new String[]{"15:30-18:00","20:30-23:00"}, new int[]{7,1},"MAX1=MAX(1530:7,1);MAX2=MAX(2030:7,1);RESULT=MAX(MAX1,MAX2)",0);
		ProgramFrameUtil.frameSql("2","�����ӵܱ�", "�⹺��������Ŀ", new String[]{"12:00-14:30"}, new int[]{7},"RESULT=MAX(1200:7)",0);


	}
	public static void channel3() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("3","ÿ��Ц��", "���ƽ�Ŀ", new String[]{"16:30-19:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1630:1-7)",0);
		ProgramFrameUtil.frameSql("3","���֪����?", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{1},"RESULT=MAX(2000:1)",0);
		ProgramFrameUtil.frameSql("3","�Ļ���ע", "���ƽ�Ŀ", new String[]{"15:30-19:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1530:1-7)",0);
		ProgramFrameUtil.frameSql("3","�������?", "���ƽ�Ŀ", new String[]{"20:00-23:30"}, new int[]{6,7},"RESULT=MAX(2000:6-7)",0);
		ProgramFrameUtil.frameSql("3","����WOMEN", "���ƽ�Ŀ", new String[]{"21:30-22:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(2130:1-7)",0);
		ProgramFrameUtil.frameSql("3","������", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{4},"RESULT=AVG(2000:4)",0);

		ProgramFrameUtil.frameSql("3","2014����ϲ����Ĭ����", "�⹺��������Ŀ", new String[]{"20:00-23:00"}, new int[]{5},"RESULT=MAX(2000:5)",0);
		ProgramFrameUtil.frameSql("3","����ѧԺ", "�⹺��������Ŀ", new String[]{"20:00-23:30"}, new int[]{1},"RESULT=MAX(2000:1)",0);
		ProgramFrameUtil.frameSql("3","�ʵ�¥��", "�⹺��������Ŀ", new String[]{"20:30-23:00"}, new int[]{3},"RESULT=MAX(2030:3)",0);
		ProgramFrameUtil.frameSql("3","��������������", "�⹺��������Ŀ", new String[]{"13:00-16:00"}, new int[]{7},"RESULT=MAX(1300:7)",0);
		ProgramFrameUtil.frameSql("3","�л���ϷԺ", "�⹺��������Ŀ", new String[]{"13:30-17:30"}, new int[]{6},"RESULT=MAX(1330:6)",0);
		ProgramFrameUtil.frameSql("3","����", "�⹺��������Ŀ", new String[]{"20:00-23:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(2000:1-7)",0);

		ProgramFrameUtil.frameSql("3","��ʾ�?", "���Ӿ�", new String[]{"18:00-21:10"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1800:1-7)",0);
		ProgramFrameUtil.frameSql("3","�Ҹ��糡", "���Ӿ�", new String[]{"12:30-15:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1230:1-7)",0);

	}
	public static void channel4() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("4","����ʱ��", "�⹺��������Ŀ", new String[]{"19:00-20:30", "22:00-23:00"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(1900:1-7);AVG2=AVG(2200:1-7);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("4","���������?", "�⹺��������Ŀ", new String[]{"17:00-19:00", "23:50-02:00", "04:00-05:30"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(1700:1-7);AVG2=AVG(2350:1-7);AVG3=AVG(0450:1-7);RESULT=MAX(AVG1,AVG2,AVG3)",0);
		ProgramFrameUtil.frameSql("4","�ǳ�������", "�⹺��������Ŀ", new String[]{"16:30-19:00"}, new int[]{7,1},"RESULT=MAX(1630:7, 1630:1)",0);
		ProgramFrameUtil.frameSql("4","��˵�糡", "���Ӿ�", new String[]{"19:40-22:10"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1940:1-7)",0);
		ProgramFrameUtil.frameSql("4","�����?", "���Ӿ�", new String[]{"17:30-19:40"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1730:1-7)",0);
		ProgramFrameUtil.frameSql("4","���糡", "���Ӿ�", new String[]{"22:10-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2210:1-7)",1);
		ProgramFrameUtil.frameSql("4","�����ͥ��?", "���Ӿ�", new String[]{"06:00-11:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0600:1-7)",0);
		ProgramFrameUtil.frameSql("4","12���?", "���Ӿ�", new String[]{"11:50-13:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1150:1-7)",0);
		ProgramFrameUtil.frameSql("4","���糡", "���Ӿ�", new String[]{"13:40-15:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1340:1-7)",0);
	}
	public static void channel5() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("5","ʳȫʳ��", "���ƽ�Ŀ", new String[]{"18:00-20:30"}, new int[]{1,2,3,4,5},"RESULT=AVG(1800:1-5)",0);
		ProgramFrameUtil.frameSql("5","��ʳ������", "���ƽ�Ŀ", new String[]{"18:00-20:30"}, new int[]{6},"RESULT=MAX(1800:6)",0);
		ProgramFrameUtil.frameSql("5","���Ƕ���������", "���ƽ�Ŀ", new String[]{"18:00-20:30"}, new int[]{1,2,3,4,5,6},"RESULT=AVG(1800:1-6)",0);
		ProgramFrameUtil.frameSql("5","��ʳ������", "���ƽ�Ŀ", new String[]{"18:00-20:30"}, new int[]{7},"RESULT=MAX(1800:7)",0);
		ProgramFrameUtil.frameSql("5","���Ƕ�����������ĩ�� ", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{7},"RESULT=MAX(1800:7)",0);
		ProgramFrameUtil.frameSql("5","��Ʒ����", "���ƽ�Ŀ", new String[]{"19:00-22:00"}, new int[]{2},"RESULT=MAX(1900:2)",0);
		ProgramFrameUtil.frameSql("5","ȺӢ��", "���ƽ�Ŀ", new String[]{"19:00-21:30"}, new int[]{6},"RESULT=MAX(1900:6)",0);
		ProgramFrameUtil.frameSql("5","�ҾӴ����?", "���ƽ�Ŀ", new String[]{"19:00-21:30"}, new int[]{3},"RESULT=MAX(1900:3)",0);

		ProgramFrameUtil.frameSql("5","��������һ��ͨ", "�⹺��������Ŀ", new String[]{"17:00-19:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1700:1-7)",0);
		ProgramFrameUtil.frameSql("5","��������", "�⹺��������Ŀ", new String[]{"15:30-18:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1530:1-7)",0);
		ProgramFrameUtil.frameSql("5","����ʱ��", "�⹺��������Ŀ", new String[]{"16:30-18:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1630:1-7)",0);
		ProgramFrameUtil.frameSql("5","֪���˼�", "�⹺��������Ŀ", new String[]{"17:30-20:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1730:1-7)",0);
		ProgramFrameUtil.frameSql("5","�ҵ�ѡ��", "�⹺��������Ŀ", new String[]{"09:30-14:00","15:00-17:30"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(0930:1-7);AVG2=AVG(1500:1-7);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("5","����������", "�⹺��������Ŀ", new String[]{"20:00-20:30","22:30-23:59"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(2000:1-7);AVG2=AVG(2230:1-7);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("5","��һ˵һ", "�⹺��������Ŀ", new String[]{"14:30-17:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1430:1-7)",0);
		ProgramFrameUtil.frameSql("5","����ʱ��", "�⹺��������Ŀ", new String[]{"22:30-23:59"}, new int[]{2,5},"RESULT=MAX(2230:2,2230:5)",0);
		ProgramFrameUtil.frameSql("5","�۽����ز�", "�⹺��������Ŀ", new String[]{"22:00-23:59"}, new int[]{3,4,7},"RESULT=AVG(2200:3,2200:4,2200:7)",0);
		ProgramFrameUtil.frameSql("5","ʰ�ţ�����", "�⹺��������Ŀ", new String[]{"13:30-15:30","23:00-23:59"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(1330:1-7);AVG2=AVG(2300:1-7);RESULT=MAX(AVG1,AVG2)",0);

		ProgramFrameUtil.frameSql("5","���������?", "���Ӿ�", new String[]{"20:30-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2030:1-7)",0);
		ProgramFrameUtil.frameSql("5","�������糡", "���Ӿ�", new String[]{"11:30-14:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1130:1-7)",0);
		}
	public static void channel6() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("6","�쿪��ʤ������������̨��", "���ƽ�Ŀ", new String[]{"17:00-20:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1700:1-7)", 0);
		ProgramFrameUtil.frameSql("6","��̳����Ұ", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1800:1-7)", 0);
		ProgramFrameUtil.frameSql("6","����", "���ƽ�Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","����������̨��", "���ƽ�Ŀ", new String[]{"21:00-24:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(2100:1-5)",0);
		ProgramFrameUtil.frameSql("6","������������", "���ƽ�Ŀ", new String[]{"11:00-13:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1100:1-7)",0);
		ProgramFrameUtil.frameSql("6","����ˤ�����ֱ���", "�⹺��������Ŀ", new String[]{"16:00-19:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1600:1-7)",0);
		ProgramFrameUtil.frameSql("6","UFC�ռ������¼���", "�⹺��������Ŀ", new String[]{"11:00-14:00"}, new int[]{1,7},"RESULT=MAX(1100:1,1100:7)",0);
		ProgramFrameUtil.frameSql("6","Ӣ������", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","������������", "�⹺��������Ŀ", new String[]{"10:30-12:30","21:00-23:00"}, new int[]{2,3},"AVG1=AVG(1030:2,1030:3);AVG2=AVG(2100:2,2100:3);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("6","T5����", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","��Ц����", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","������֮·", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","ȭ����ʤ", "�⹺��������Ŀ", new String[]{"11:00-14:00"}, new int[]{1},"RESULT=MAX(1100:1)",0);
		ProgramFrameUtil.frameSql("6","һ�������?", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","������", "�⹺��������Ŀ", new String[]{"22:00-23:59"}, new int[]{4},"RESULT=MAX(2200:4)",0);
		ProgramFrameUtil.frameSql("6","����ʮ��", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","�����㹻����", "�⹺��������Ŀ", new String[]{"18:30-21:00","20:00-22:00"}, new int[]{4,6},"RESULT=MAX(1830:4,1830:6,2000:4,2000:6)",0);
		ProgramFrameUtil.frameSql("6","����߶���?", "�⹺��������Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)",0);
		ProgramFrameUtil.frameSql("6","POWER�����?", "�⹺��������Ŀ", new String[]{"6:00-8:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(0600:1-5)",0);

	}
	public static void channel7() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("7","��������", "���ƽ�Ŀ", new String[]{"20:00-23:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(2000:1-7)",0);
		ProgramFrameUtil.frameSql("7","���տ�ͥ", "���ƽ�Ŀ", new String[]{"17:00-19:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1700:1-7)",0);
		ProgramFrameUtil.frameSql("7","����", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(1800:1-5)",0);
		ProgramFrameUtil.frameSql("7","��˵��", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(1800:1-5)",0);
		ProgramFrameUtil.frameSql("7","���۴���ʦ", "���ƽ�Ŀ", new String[]{"19:00-21:00"}, new int[]{1,2,3,4},"RESULT=AVG(1900:3,1900:4)",0);
		ProgramFrameUtil.frameSql("7","���۴�ͥ", "���ƽ�Ŀ", new String[]{"19:00-21:00"}, new int[]{5},"RESULT=MAX(1900:5)",0);

		ProgramFrameUtil.frameSql("7","��������", "�⹺��������Ŀ", new String[]{"17:00-19:00"}, new int[]{7},"RESULT=MAX(1700:7)",0);
		ProgramFrameUtil.frameSql("7","�ƽ�������", "�⹺��������Ŀ", new String[]{"19:00-20:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1900:1-7)",0);
		ProgramFrameUtil.frameSql("7","�����й�60��", "�⹺��������Ŀ", new String[]{"11:00-14:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1100:1-7)",0);
		ProgramFrameUtil.frameSql("7","��սСѧ��", "�⹺��������Ŀ", new String[]{"16:00-19:00","19:30-21:30"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(1600:1-7);AVG2=AVG(1930:1-7);RESULT=MAX(AVG1,AVG2)",0);
		ProgramFrameUtil.frameSql("7","�й���", "�⹺��������Ŀ", new String[]{"18:00-20:00"}, new int[]{6,7},"RESULT=MAX(1800:6, 1800:7)",0);
		ProgramFrameUtil.frameSql("7","�Ļ��й�", "�⹺��������Ŀ", new String[]{"19:00-21:00"}, new int[]{6,7},"RESULT=MAX(1900:6, 1900:7)",0);
		ProgramFrameUtil.frameSql("7","�����й�60��", "�⹺��������Ŀ", new String[]{"14:30-16:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1430:1-7)",0);
		ProgramFrameUtil.frameSql("7","���۽�", "�⹺��������Ŀ", new String[]{"12:00-15:00"}, new int[]{6,7},"RESULT=MAX(1200:6,1200:7)",0);
		ProgramFrameUtil.frameSql("7","������", "�⹺��������Ŀ", new String[]{"18:00-20:00"}, new int[]{6,7},"RESULT=MAX(1800:6,1800:7)",0);
		ProgramFrameUtil.frameSql("7","��¼�й�", "�⹺��������Ŀ", new String[]{"22:00-24:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2200:1-7)",0);
		ProgramFrameUtil.frameSql("7","̽��", "�⹺��������Ŀ", new String[]{"9:00-11:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(0900:1-7)",0);
		ProgramFrameUtil.frameSql("7","������", "�⹺��������Ŀ", new String[]{"0:00-3:00","3:30-6:00","8:00-10:00"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(0000:1-7);AVG2=AVG(0330:1-7);AVG3=AVG(0800:1-7);RESULT=MAX(AVG1,AVG2,AVG3)",0);
		ProgramFrameUtil.frameSql("7","����", "�⹺��������Ŀ", new String[]{"1:00-3:00","4:00-6:00","23:00-01:00"}, new int[]{1,2,3,4,5,6,7},"AVG1=AVG(0100:1-7);AVG2=AVG(0400:1-7);AVG3=AVG(2300:1-7);RESULT=MAX(AVG1,AVG2,AVG3)",0);
		ProgramFrameUtil.frameSql("7","�����ι���", "�⹺��������Ŀ", new String[]{"19:30-21:30","8:00-10:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(1930:1-7,0800:1-7)",0);


	}
	public static void channel8() throws Exception {
		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("8","����������", "���ƽ�Ŀ", new String[]{"20:00-22:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2000:1-7)",0);
		ProgramFrameUtil.frameSql("8","�й�������", "�⹺��������Ŀ", new String[]{"12:00-15:00"}, new int[]{2,3,4,5,6},"RESULT=AVG(1200:2-6)",0);
		ProgramFrameUtil.frameSql("8","С�������ֲ�", "�⹺��������Ŀ", new String[]{"15:00-17:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1500:1-7)",0);
		ProgramFrameUtil.frameSql("8","�����ַ���", "�⹺��������Ŀ", new String[]{"15:00-16:30"}, new int[]{6,7},"RESULT=MAX(1500:6-7)",0);
		ProgramFrameUtil.frameSql("8","�ϰ��ǳ���", "�⹺��������Ŀ", new String[]{"13:30-16:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1330:1-7)",0);
	}
	public static void channel9() throws Exception {

		ProgramFrameUtil ProgramFrameUtil = new ProgramFrameUtil("2015-03-30","2015-04-05");
		ProgramFrameUtil.frameSql("9","���������?", "���ƽ�Ŀ", new String[]{"17:30-20:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(1730:1-5)", 0);
		ProgramFrameUtil.frameSql("9","����˵�¶�", "���ƽ�Ŀ", new String[]{"20:00-22:00"}, new int[]{2,3,4,5,6},"RESULT=AVG(2000:2-5)", 0);
		ProgramFrameUtil.frameSql("9","��������", "���ƽ�Ŀ", new String[]{"20:00-22:00"}, new int[]{1},"RESULT=MAX(2000:1)", 0);
		ProgramFrameUtil.frameSql("9","���ֶ�", "���ƽ�Ŀ", new String[]{"13:00-16:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1300:1-7)", 0);
		ProgramFrameUtil.frameSql("9","��ҽ��˳", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{6,7},"RESULT=MAX(1800:1-7)", 0);
		//ProgramFrameUtil.frameSql("9","����ҽ��˳�����ܣ�", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{6,7},"RESULT=MAX(1800:6-7)", 0);
		//ProgramFrameUtil.frameSql("9","����ҽ��˳�����գ�", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{1,2,3,4,5},"RESULT=AVG(1800:1-5)", 0);
		ProgramFrameUtil.frameSql("9","�����?", "���ƽ�Ŀ", new String[]{"17:30-20:00"}, new int[]{6},"RESULT=MAX(1730:6)", 0);
		ProgramFrameUtil.frameSql("9","���״��֮��ĸ�������?", "���ƽ�Ŀ", new String[]{"18:00-20:00"}, new int[]{7},"RESULT=MAX(1800:7)", 0);
		ProgramFrameUtil.frameSql("9","���״��?", "���ƽ�Ŀ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)", 0);

		ProgramFrameUtil.frameSql("9","���鱣��ս", "�⹺��������Ŀ", new String[]{"12:00-15:00"}, new int[]{3,5,7},"RESULT=AVG(1200:3,1200:5,1200:7)", 0);
		ProgramFrameUtil.frameSql("9","��������", "�⹺��������Ŀ", new String[]{"20:00-22:30"}, new int[]{7},"RESULT=MAX(2000:7)", 0);
		ProgramFrameUtil.frameSql("9","�л�ֱ˵", "�⹺��������Ŀ", new String[]{"17:00-19:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1700:1-7)", 0);
		ProgramFrameUtil.frameSql("9","������ǧ", "�⹺��������Ŀ", new String[]{"18:00-19:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(1800:1-7)", 0);
		ProgramFrameUtil.frameSql("9","�˼䴺ɫ", "�⹺��������Ŀ", new String[]{"21:00-22:30"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2100:1-7)", 0);
		ProgramFrameUtil.frameSql("9","����ʱ��", "�⹺��������Ŀ", new String[]{"22:30-24:00"}, new int[]{3,6},"RESULT=MAX(2230:3,2230:6)", 0);
		ProgramFrameUtil.frameSql("9","����Ī��", "�⹺��������Ŀ", new String[]{"12:00-14:30"}, new int[]{2,4},"RESULT=MAX(1200:2,1200:4)", 0);
		ProgramFrameUtil.frameSql("9","�۽����ز�", "�⹺��������Ŀ", new String[]{"22:00-23:59"}, new int[]{1,4,5},"RESULT=AVG(2200:1,2200:4,2200:5)", 0);
		ProgramFrameUtil.frameSql("9","�������?", "�⹺��������Ŀ", new String[]{"23:00-01:00"}, new int[]{1,2,3,4,5,6,7},"RESULT=AVG(2300:1-7)", 0);

		ProgramFrameUtil.frameSql("9","��Ӱ", "��Ӱ", new String[]{"00:00-23:59"}, new int[]{1,2,3,4,5,6,7},"RESULT=MAX(0000:1-7)", 0);
	}
	*/
	public List<String> frameSql(String frameName, String channelId, String programName,
			String programType, String[] time, int[] weekDay, String rule, String playWay, String playDays, String timePerood,
			int addDay) throws Exception {
		List<String> list = new ArrayList<String>();
		
		StringBuffer CUSTOM_PROGRAM_FRAMEBAK = new StringBuffer(
				"INSERT INTO CUSTOM_PROGRAM_FRAME VALUES\n");
		CUSTOM_PROGRAM_FRAMEBAK.append("('" + programName + "'," + channelId
				+ ",TO_DATE('" + beginDate + "','yyyy-mm-dd'),TO_DATE('"
				+ endDate + "','yyyy-mm-dd'),'" + frameName + "','" + rule + "','" + playDays + "','" + timePerood +  "','" + playWay + "','" + programType + "')");
		list.add(CUSTOM_PROGRAM_FRAMEBAK.toString());
		StringBuffer CUSTOM_PROGRAM_FRAME_DETAILBAK = new StringBuffer();
		boolean crossWeek = false;
		boolean crossBeginDay = false;
		boolean crossEndDay = false;
		String[] ruleArr = rule.split(";");
		for (int i = 0; i < weekDay.length; i++) {
			if (i > 0 && weekDay[i] <= weekDay[i - 1]) {
				crossWeek = true;
			}
			crossBeginDay = false;
			for (int j = 0; j < time.length; j++) {
				// ���˹�����û�й涨������
				String startTime = time[j].split("-")[0];
				String ruleStartTime = startTime.replace(":", "");
				boolean ifExtra = true;
				for (String checkRule : ruleArr) {
					if (checkRule.indexOf(ruleStartTime) >= 0 && checkRule.indexOf(ruleStartTime + ":" + weekDay[i]) >= 0) {
						ifExtra = false;
						break;
					}
				}
				
				if (ifExtra) {
					continue;
				}
				
				if (j > 0
						&& Integer.parseInt(time[j].split("-")[0].replaceAll(
								":", "")) <= Integer.parseInt(time[j - 1]
								.split("-")[0].replaceAll(":", ""))) {
					crossBeginDay = true;
				}
				if (Integer.parseInt(time[j].split("-")[1].replaceAll(":", "")) 
						<= 
						Integer.parseInt(time[j].split("-")[0].replaceAll(":", ""))) {
					crossEndDay = true;
				}
				String beginDay = weekDayList.get(weekDay[i] - 1);
				String endDay = weekDayList.get(weekDay[i] - 1);
				if (crossWeek) {
					beginDay = dataUtil.addDay(beginDay, "yyyy-MM-dd", 7);
					endDay = dataUtil.addDay(endDay, "yyyy-MM-dd", 7);
				}
				if (crossBeginDay) {
					beginDay = dataUtil.addDay(beginDay, "yyyy-MM-dd", 1);
					endDay = dataUtil.addDay(endDay, "yyyy-MM-dd", 1);
				}
				if (crossEndDay) {
					endDay = dataUtil.addDay(endDay, "yyyy-MM-dd", 1);
				}
				if (crossWeek || crossBeginDay || crossEndDay) {
					CUSTOM_PROGRAM_FRAME_DETAILBAK
							.append("INSERT INTO CUSTOM_PROGRAM_FRAME_DETAIL VALUES\n");
					CUSTOM_PROGRAM_FRAME_DETAILBAK.append("('" + programName
							+ "'," + channelId + ",TO_DATE('"
							+ beginDay
							+ "','yyyy-mm-dd'),TO_DATE('"
							+ endDay
							+ "','yyyy-mm-dd'),'" + time[j].split("-")[0]
							+ ":00','" + time[j].split("-")[1] + ":59','"
							+ weekName[weekDay[i] - 1] + "','1','"
							+ programType + "',null,null,null,null,TO_DATE('" + beginDate
							+ "','yyyy-mm-dd'),TO_DATE('" + endDate
							+ "','yyyy-mm-dd'),'"
							+ frameName + "','" + programName +"',null,null)\n");
				} else {
					CUSTOM_PROGRAM_FRAME_DETAILBAK
							.append("INSERT INTO CUSTOM_PROGRAM_FRAME_DETAIL VALUES\n");
					CUSTOM_PROGRAM_FRAME_DETAILBAK.append("('" + programName
							+ "'," + channelId + ",TO_DATE('"
							+ weekDayList.get(weekDay[i] - 1)
							+ "','yyyy-mm-dd'),TO_DATE('"
							+ weekDayList.get(weekDay[i] - 1)
							+ "','yyyy-mm-dd'),'" + time[j].split("-")[0]
							+ ":00','" + time[j].split("-")[1] + ":59','"
							+ weekName[weekDay[i] - 1] + "','1', '"
							+ programType + "',null,null,null,null,TO_DATE('"
							+ beginDate + "','yyyy-mm-dd'),TO_DATE('" + endDate
							+ "','yyyy-mm-dd'),'"
							+ frameName + "','" + programName +"',null,null)\n");
				}
				
				list.add(CUSTOM_PROGRAM_FRAME_DETAILBAK.toString());
				CUSTOM_PROGRAM_FRAME_DETAILBAK.setLength(0);

			}
		}
		
		return list;
	}

}
