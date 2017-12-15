package com.viewstar.jsdatamonitor.util;


import java.io.UnsupportedEncodingException;

/**
 * ˵����������
 *
 * @classname Common
 * @date 2007-12-6
 * @author Justin
 */
public class StringConvertor
{
	// ����ת��С����
	public static String convertEncoding(String source, String sencoding,
			String dencoding) throws UnsupportedEncodingException
	{
		byte[] byts = source.getBytes(sencoding);
		String result = new String(byts, dencoding);
		return result;
	}

	// ��ISO-8859-1ת��ΪGBK
	public static String convertEncodingtoGBK(String source) throws UnsupportedEncodingException
	{
		return convertEncoding(source, "ISO-8859-1", "UTF-8");

	}
}
