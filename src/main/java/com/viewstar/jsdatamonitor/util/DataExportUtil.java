package com.viewstar.jsdatamonitor.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DataExportUtil {
	
	/**
	 * �ļ���׺��(csv)��".csv"
	 */
	public static final String FILE_SUFIXX_NAME_CSV = ".csv";
	
	/**
	 * �ļ���׺��(excel)��".xlsx"
	 */
	public static final String FILE_SUFIXX_NAME_EXCEL = ".xlsx";
	
	/**
	 * �س�����"\r"
	 */
	public static final int SYMBOL_CR = 13;
	
	/**
	 * ���з���"\n"
	 */
	public static final int SYMBOL_LF = 10;
	
	/**
	 * ���ţ�","
	 */
	public static final int SYMBOL_COMMA = 44;
	
	/**
	 * ˫���ţ�"\""
	 */
	public static final int SYMBOL_DOUBLE_QUOTE = 34;
	
	/**
	 * ���������ļ�
	 * headerMap��ͷ��KeyӦ��dataList���������Keyһ�²���ȡ����Ӧ�е�����
	 * �ַ�����Ĭ��ΪGBK
	 * 
	 * @param fileDir �ļ�����Ŀ¼ 
	 * @param displayname �ļ���
	 * @param dataList �����б�
	 * @param headerMap ��ͷ��ʶ
	 * @param fileType �ļ����� 0-CSV;1-EXCEL;
	 * @throws Exception
	 */
	public static String exportData(String fileDir, String displayname, List<Map<String, Object>> dataList,
			LinkedHashMap<String, Object> headerMap, String fileType) throws Exception {
		return exportData(fileDir, displayname, dataList, headerMap, fileType, "GBK");
	}
	
	/**
	 * ���������ļ�
	 * headerMap��ͷ��KeyӦ��dataList���������Keyһ�²���ȡ����Ӧ�е�����
	 * 
	 * @param fileDir �ļ�����Ŀ¼ 
	 * @param displayname �ļ���
	 * @param dataList �����б�
	 * @param headerMap ��ͷ��ʶ
	 * @param fileType �ļ����� 0-CSV;1-EXCEL;
	 * @param csvCharsetName CSV�ַ�����
	 * @return �ļ�����·��
	 * @throws IOException 
	 * @throws Exception
	 */
	public static String exportData(String fileDir, String displayname, List<Map<String, Object>> dataList,
			LinkedHashMap<String, Object> headerMap, String fileType, String csvCharsetName) throws IOException {

		File directory = new File(fileDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File file = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			
			// CSV�ļ�
			if ("0".equals(fileType)) {
				
				// �����ļ���
				String filenamedisplay = new StringBuilder(displayname).append(FILE_SUFIXX_NAME_CSV).toString();
				
				file = new File(fileDir, filenamedisplay);
				
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
				
				// �����ļ�
				generateCsv(bufferedOutputStream, dataList, headerMap, csvCharsetName);
				
			// EXCEL�ļ�
			} else if ("1".equals(fileType)) {
				
				// �����ļ���
				String filenamedisplay = new StringBuilder(displayname).append(FILE_SUFIXX_NAME_EXCEL).toString();

				file = new File(fileDir, filenamedisplay);
				
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
				
				// �����ļ�
				generateExcel(bufferedOutputStream, dataList, headerMap);
				
			} else {
				throw new IllegalArgumentException("����ʶ����ļ����ͣ�?" + String.valueOf(fileType) + "��������ѡ��0-CSV;1-EXCEL;");
			}
			
			return file.getAbsolutePath();
		} finally {
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}
		}
	}
	
	/**
	 * ����CSV�ļ�
	 * headerMap��ͷ��KeyӦ��dataList���������Keyһ�²���ȡ����Ӧ�е�����
	 * 
	 * @param outputStream ��������ļ��������
	 * @param dataList �����б�
	 * @param headerMap ��ͷ��ʶ
	 * @param charsetName �ַ�����
	 * @throws IOException д�ļ��쳣
	 */
	public static void generateCsv(OutputStream outputStream, List<Map<String, Object>> dataList,
			LinkedHashMap<String, Object> headerMap, String charsetName) throws IOException {
		
		// дCSV�ļ�
		// ������Key
		int itemLength = headerMap.size();
		String[] columnKeys = new String[itemLength];
		{
			// ͷ��
			int i = 0;
			for (Entry<String, Object> entry : headerMap.entrySet()) {
				
				// дͷ����
				writeItem(outputStream, i == itemLength - 1, entry.getValue().toString(), charsetName);
				
				// ����������Key
				columnKeys[i] = entry.getKey();
				i++;
			}
		}
		
		// ����
		for (int i = 0; i < dataList.size(); i++) {
			Map<String, Object> lineData = dataList.get(i);
			for (int j = 0; j < itemLength; j++) {
				// д������
				writeItem(outputStream, j == itemLength - 1, lineData.get(columnKeys[j]) == null ? "" : lineData.get(columnKeys[j]).toString(), charsetName);
			}
		}
	}
	
	/**
	 * ��CSV�ļ���д��һ��������
	 * �����һ���ʽ"itemContent",
	 * ���һ���ʽ"itemContent"\r\n
	 * 
	 * @param outputStream �����?
	 * @param itemContent ����������
	 * @param isLastItem �Ƿ�Ϊ���һ��?
	 * @throws IOException д���쳣
	 */
	public static void writeItem(OutputStream outputStream, boolean isLastItem, String itemContent, String charsetName) throws IOException {
		
		// д��"\""
		outputStream.write(SYMBOL_DOUBLE_QUOTE);
		
		// ������ݴ���?
		if (itemContent != null) {
			
			// д������
			outputStream.write(itemContent.getBytes(charsetName));
		}
		
		// д��"\""
		outputStream.write(SYMBOL_DOUBLE_QUOTE);
		
		// �������һ��?
		if (!isLastItem) {
			
			// д��","
			outputStream.write(SYMBOL_COMMA);
			
			// �����һ��?
		} else {
			
			// д�뻻��
			outputStream.write(SYMBOL_CR);
			outputStream.write(SYMBOL_LF);
		}
	}
	
	/**
	 * ����Excel�ļ�
	 * headerMap��ͷ��KeyӦ��dataList���������Keyһ�²���ȡ����Ӧ�е�����
	 * 
	 * @param outputStream ��������ļ��������
	 * @param dataList �����б�
	 * @param headerMap ��ͷ��ʶ
	 * @throws IOException 
	 * @throws Exception
	 */
	public static void generateExcel(OutputStream outputStream, List<Map<String, Object>> dataList,
			LinkedHashMap<String, Object> headerMap) throws IOException {
		
		// �����µ�Excel ������
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("data");
		
		// дCSV�ļ�
		// ������Key
		int itemLength = headerMap.size();
		String[] columnKeys = new String[itemLength];
		{
			Row row = sheet.createRow(0);
			// ͷ��
			int i = 0;
			for (Entry<String, Object> entry : headerMap.entrySet()) {
				
				// дͷ����
				writeItem(row, i, entry.getValue().toString());
				
				// ����������Key
				columnKeys[i] = entry.getKey();
				i++;
			}
		}
		
		// ����
		for (int i = 0; i < dataList.size(); i++) {
			Row row = sheet.createRow(i + 1);
			
			Map<String, Object> lineData = dataList.get(i);
			for (int j = 0; j < itemLength; j++) {
				
				// д������
				writeItem(row, j, lineData.get(columnKeys[j]) == null ? "" : lineData.get(columnKeys[j]).toString());
			}
		}
		
		workbook.write(outputStream);
	}
	
	/**
	 * ��EXCEL�ļ���д��һ��������
	 * 
	 * @param row ������
	 * @param cellIndex ��Ԫ�����?
 	 * @param itemContent ����������
	 */
	public static void writeItem(Row row, int cellIndex, String itemContent) {
		
		Cell cell = row.createCell(cellIndex);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(itemContent);
	}
}
