package com.viewstar.jsdatamonitor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessBar {

	public static List IPTVUserInfolist=new ArrayList();

	public static List ChannelPlaytimelist=new ArrayList();

	public static List dataimportlist=new ArrayList();

	public static List dataETLlist=new ArrayList();

	public static List datalist=new ArrayList();

	public static List userDataETLlist=new ArrayList();

	public static List programListlist=new ArrayList();

	public static List programListlistrunbat=new ArrayList();

	public static List userepglogdownloadlist=new ArrayList();

	public static List ztlist=new ArrayList();

	public static List columnList = new ArrayList();

	public static List channelList = new ArrayList();//����ֱ�����ݴ������ʾ���?

	public static List aprioriETLlist=new ArrayList();

	public static List productETLlist=new ArrayList();

	public static List kemanlist=new ArrayList();

	public static List allrtgetlList = new ArrayList();

	public static List jobList = new ArrayList();

	public static List dataMonthlyETLlist=new ArrayList();

	public static List dataminingList = new ArrayList();

	public static List epgrecommendList=new ArrayList();

	public static List useractiveList=new ArrayList();

	public static List C3LTInstallList=new ArrayList();

	public static List C3TearDownUsers=new ArrayList();

	public static List ADList=new ArrayList();

	public static List CustomProgramList = new ArrayList();

	public static List PlatFormList = new ArrayList();

	public static List ViewList = new ArrayList();

	public static List weekReport = new ArrayList(); // ֱ���ܱ�����

	public static List columnlist = new ArrayList();
	
	public static List channelColProgramList = new ArrayList();
	
	public static Map<String,Integer> reportMap= new HashMap<String,Integer>();

	public static List ChargesFailList = new ArrayList();

	public static List setList(List list,String msg,int num)
	{
		if(list.size()==num)
		{
			list.add(0,msg);
			list.remove(list.size()-1);
			return list;
		}else
		{
			list.add(0,msg);
			return list;
		}

	}
	public static void main(String[] args) throws Exception {

		List list=new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		ProcessBar.weekReport=setList(ProcessBar.weekReport,"1qweqweqweqwe",10);

		System.out.println(ProcessBar.weekReport);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"2",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"3",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"4",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"5",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"6",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"7",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"8",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"9",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"10",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"11",10);
		ProcessBar.IPTVUserInfolist=setList(ProcessBar.IPTVUserInfolist,"12",10);



	}
}
