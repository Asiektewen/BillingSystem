/**
 * 
 */
package com.telecom.billing.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.telecom.billing.model.CallDetail;
import com.telecom.billing.model.RateHistory;
import com.telecom.billing.model.TrafficSummary;

/**
 * @author Eric
 * 
 */
public class ExcelUtils {

	public static String[] TRAFFIC_SUMMARY_HEADER = { "Service Name",
			"From Country Name", "To Country Name", "Total Minutes Of Calls" };
	public static String[] RATES_HEADER = { "dest", "peak", "offPeak" };
	public static String CALL_FILE_TYPE = "call";
	public static String RATES_FILE_TYPE = "rates";
	public static String TRAFFIC_SUMMARY = "TrafficSummary";

	public static String generateExcelFile(String fileName,
			String[] sheetHeader, Map<String, Collection> data)
			throws Exception {
		// create the workbook;

		Workbook wb = new HSSFWorkbook();
		Iterator<Entry<String, Collection>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Entry pair = (Entry) it.next();
			String sheetName = (String) pair.getKey();
			Collection sheetData = (Collection) pair.getValue();
			createSingleSheet(wb, sheetHeader, sheetName, sheetData);
		}

		String path = getOutPutDir() + fileName + ".xls";
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		if (null != fos) {
			fos.close();
		}
		return path;

	}

	public static List readExcelFile(File file, String fileType)
			throws Exception {
		List objList = new ArrayList();
		String date = file.getName().split("_")[1];
		Workbook wb = new HSSFWorkbook(new FileInputStream(file));
		if (fileType.equals(CALL_FILE_TYPE)) {
			Sheet sheet = wb.getSheetAt(0);
			for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) {
				Row row  = sheet.getRow(i);
				CallDetail cd = new CallDetail();
				cd.setSrcCountryId(new Integer(row.getCell(0).getStringCellValue()));
				cd.setDestCountryId(new Integer(row.getCell(1).getStringCellValue()));
				cd.setSrcPhoneNumber(row.getCell(2).getStringCellValue());
				cd.setDestPhoneNumber(row.getCell(3).getStringCellValue());
				cd.setDuration(new Integer(row.getCell(4).getStringCellValue()));
				cd.setCallDate(new Date(row.getCell(5).getStringCellValue()));
				cd.setCallTime(row.getCell(6).getStringCellValue());
				objList.add(cd);
			}
		} else {
			for (int k = 0; k < 100; k++) {
				if (null != wb.getSheetAt(k)) {
					Sheet sheet = wb.getSheetAt(k);
					RateHistory rh= new RateHistory();
					for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) {
						String sheetName = sheet.getSheetName();
						Row row  = sheet.getRow(i);
						rh.setStartTime(new Date(date));
						rh.setServviceType(sheetName.split("_")[0]);
						rh.setSrcCountry(sheetName.split("_")[1]);
						rh.setDestCountryId(new Integer(row.getCell(0).getStringCellValue()));
						rh.setPeakRate(new Double(row.getCell(1).getStringCellValue()));
						rh.setOffpeakRate(new Double(row.getCell(2).getStringCellValue()));
						objList.add(rh);
					}
				}
			}

		}

		return objList;
	}

	private static void createSingleSheet(Workbook wb, String[] sheetHeader,
			String sheetName, Collection sheetData) {

		Sheet sheet = wb.createSheet(sheetName);
		sheet.setColumnWidth(0, 32 * 120);
		sheet.setColumnWidth(1, 32 * 150);
		sheet.setColumnWidth(2, 32 * 150);
		sheet.setColumnWidth(3, 32 * 200);
		Row row = sheet.createRow(0);
		row.setHeightInPoints(30);

		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		HSSFFont font = (HSSFFont) wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cs.setFont(font);
		cs.setFillBackgroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		for (int i = 0; i < sheetHeader.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(cs);
			cell.setCellValue(sheetHeader[i]);
		}
		Iterator it = sheetData.iterator();
		int rowNo = 1;
		if (sheetName.startsWith(TRAFFIC_SUMMARY)) {
			while (it.hasNext()) {
				TrafficSummary tr = (TrafficSummary) it.next();
				row = sheet.createRow(rowNo);
				Cell cell = row.createCell(0);
				cell.setCellValue(tr.getServiceName());
				cell = row.createCell(1);
				cell.setCellValue(tr.getFromCtyName());
				cell = row.createCell(2);
				cell.setCellValue(tr.getToCtyName());
				cell = row.createCell(3);
				cell.setCellValue(tr.getTotalMinsOfCalls());
			}

		} else {
			while (it.hasNext()) {
				RateHistory tr = (RateHistory) it.next();
				row = sheet.createRow(rowNo);
				Cell cell = row.createCell(0);
				cell.setCellValue(tr.getDestCountry());
				cell = row.createCell(1);
				cell.setCellValue(tr.getPeakRate());
				cell = row.createCell(2);
				cell.setCellValue(tr.getOffpeakRate());
			}
		}

	}

	public static String getOutPutDir() {
		String path = System.getProperty("user.dir") + "\\output";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		return path;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Map map = new HashMap();
		List arrList = new ArrayList();
		arrList.add(new Object());

		map.put("TrafficSummary", (Collection) arrList);

		ExcelUtils.generateExcelFile("TrafficSummary_201411",
				TRAFFIC_SUMMARY_HEADER, map);
	}

}
