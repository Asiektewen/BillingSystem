/**
 * 
 */
package com.telecom.billing.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import com.telecom.billing.model.RateHistoryTemp;
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
		Workbook wb = new HSSFWorkbook(new FileInputStream(file));
		if (fileType.equals(CALL_FILE_TYPE)) {
			Sheet sheet = wb.getSheetAt(0);
			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getLastRowNum() + 1; i++) {
				Row row = sheet.getRow(i);
				CallDetail cd = new CallDetail();
				cd.setSrcCountryId((int) row.getCell(0).getNumericCellValue());
				cd.setDestCountryId((int) row.getCell(1).getNumericCellValue());
				cd.setSrcPhoneNumber(Integer.toString((int) row.getCell(2)
						.getNumericCellValue()));
				cd.setDestPhoneNumber(Integer.toString((int) row.getCell(3)
						.getNumericCellValue()));
				cd.setDuration((int) row.getCell(4).getNumericCellValue());
				cd.setCallDate(new Date((long) row.getCell(5)
						.getNumericCellValue()));
				cd.setCallTime(Integer.toString((int) row.getCell(6)
						.getNumericCellValue()));
				objList.add(cd);
			}
		} else {
			String partName = file.getName().split("_")[1];
			Date date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
					.parse(partName.substring(0, partName.length() - 4));
			for (int k = 0; k < wb.getNumberOfSheets(); k++) {
				if (null != wb.getSheetAt(k)) {
					Sheet sheet = wb.getSheetAt(k);
					RateHistoryTemp rh = new RateHistoryTemp();
					for (int i = sheet.getFirstRowNum() + 1; i < sheet
							.getLastRowNum() + 1; i++) {
						String sheetName = sheet.getSheetName();
						Row row = sheet.getRow(i);
						rh.setStartTime(date);
						rh.setServviceType(sheetName.split("_")[0]);
						rh.setSrcCountry(sheetName.split("_")[1]);
						rh.setDestCountryId((int) row.getCell(0)
								.getNumericCellValue());
						rh.setPeakRate(new Double(row.getCell(1)
								.getNumericCellValue()));
						rh.setOffpeakRate(new Double(row.getCell(2)
								.getNumericCellValue()));
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
				rowNo++;
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
				rowNo++;
			}
		}

	}

	public static String getOutPutDir() {
		String path = ExcelUtils.class.getClassLoader().getResource("/")
				.getPath();
		path = path.substring(0, path.length() - 9) + "\\output\\";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		return path;
	}

	public static String getInPutDir() {
		String path = ExcelUtils.class.getClassLoader().getResource("/")
				.getPath();
		path = path.substring(0, path.length() - 9) + "\\input\\";
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
		// generate the traffic summary;
		// for(int i =0;i<50;i++){
		// TrafficSummary traffic = new TrafficSummary();
		// traffic.setServiceName("serveice"+i);
		// traffic.setFromCtyName("from cty"+i);
		// traffic.setToCtyName("to CTY"+i);
		// traffic.setTotalMinsOfCalls((double) i);
		// arrList.add(traffic);
		// }
		// map.put("TrafficSummary", (Collection) arrList);
		// ExcelUtils.generateExcelFile("TrafficSummary_201411",
		// TRAFFIC_SUMMARY_HEADER, map);
		//
		// // generate the rates sheets ;
		// Map ratesMap = new HashMap();
		// List rateList = new ArrayList();
		// for(int i =0;i<50;i++){
		// RateHistory rate = new RateHistory();
		// rate.setDestCountry("dest Cty"+i);
		// rate.setPeakRate((double) i*10);;
		// rate.setOffpeakRate((double) (i*5));
		// rateList.add(rate);
		// }
		// ratesMap.put("Spectra_USA", (Collection) rateList);
		// ratesMap.put("Deluxe_USA", (Collection) rateList);
		// ratesMap.put("GACB_France", (Collection) rateList);
		//
		// ExcelUtils.generateExcelFile("Rates_201411",
		// RATES_HEADER, ratesMap);
		// System.getProperty("user.dir") + "\\output\\";
		File callFile = new File(System.getProperty("user.dir")
				+ "\\input\\Calls_Nov2013.xls");
		List callList = readExcelFile(callFile, CALL_FILE_TYPE);
		System.out.println(callList.size());

		File ratefile = new File(System.getProperty("user.dir")
				+ "\\input\\Rates_20130901.xls");
		List rateList = readExcelFile(ratefile, RATES_FILE_TYPE);
		System.out.println(rateList.size());

		System.out.println("done!");
	}

}
