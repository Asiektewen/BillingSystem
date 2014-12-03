/**
 * 
 */
package com.telecom.billing.services.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.Utils.ExcelUtils;
import com.telecom.billing.Utils.PdfUtils;
import com.telecom.billing.dao.BillDAO;
import com.telecom.billing.dao.CallDetailDAO;
import com.telecom.billing.dao.CommissionDAO;
import com.telecom.billing.dao.CountryInfoDAO;
import com.telecom.billing.dao.CustomerDAO;
import com.telecom.billing.dao.RateHistoryDAO;
import com.telecom.billing.dao.RateHistoryTempDAO;
import com.telecom.billing.dao.RatesUpdateHistoryDAO;
import com.telecom.billing.dao.ServiceInfoDAO;
import com.telecom.billing.dao.TrafficSummaryDAO;
import com.telecom.billing.model.CallDetail;
import com.telecom.billing.model.CountryInfo;
import com.telecom.billing.model.Customer;
import com.telecom.billing.model.RateHistoryTemp;
import com.telecom.billing.model.RatesUpdateHistory;
import com.telecom.billing.model.ServiceInfo;
import com.telecom.billing.services.FileService;

/**
 * @author Eric
 * 
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

	@Autowired
	@Qualifier("rateHistoryTempDAO")
	public RateHistoryTempDAO rateHistoryTempDAO;

	@Autowired
	@Qualifier("rateHistoryDAO")
	public RateHistoryDAO rateHistoryDAO;

	@Autowired
	@Qualifier("callDetailDAO")
	public CallDetailDAO callDetailDAO;

	@Autowired
	@Qualifier("customerDAO")
	public CustomerDAO customerDAO;

	@Autowired
	@Qualifier("ratesUpdateHistoryDAO")
	public RatesUpdateHistoryDAO ratesUpdateHistoryDAO;

	@Autowired
	@Qualifier("serviceInfoDAO")
	public ServiceInfoDAO serviceInfoDAO;

	@Autowired
	@Qualifier("countryInfoDAO")
	public CountryInfoDAO countryInfoDAO;

	@Autowired
	@Qualifier("billDAO")
	public BillDAO billDAO;

	@Autowired
	@Qualifier("trafficSummaryDAO")
	public TrafficSummaryDAO trafficSummaryDAO;

	@Autowired
	@Qualifier("commissionDAO")
	public CommissionDAO commissionDAO;	
	
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public String generateMonthlyBills(String fileName) throws Exception {
		List customers = customerDAO.findAllCustomer();
		// String month = processBillBatch(fileName);
		String month = fileName.split("_")[1];

		for (int i = 0; i < customers.size(); i++) {
			Customer customer = (Customer) customers.get(i);
			String phoneNO = customer.getPhoneNumber();
			Map billMap = new HashMap();
			List billData = billDAO.getBillListbySrcPhone(phoneNO);

			billMap.put("Bill_data", billData);
			PdfUtils.generateMonthlyBill("Bill_" + phoneNO + "_" + month,
					billMap);
		}
		return ExcelUtils.getOutPutDir(month);
	}

	@Override
	@Transactional
	public void processBillBatch(String fileName) throws ParseException {
		String month = fileName.split("_")[1];
		Date endDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
				.parse("30-" + month);

		Date startDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
				.parse("01-" + month);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
		billDAO.generateMonthlyBill(df.format(startDate),
				df.format(endDate));

	}

	@Override
	@Transactional
	public String generateRateSheet(String fileName) throws Exception {
		Map dataMap = new HashMap();
		String date = fileName.split("_")[3];
		String service = fileName.split("_")[1];
		String srcCty = fileName.split("_")[2];
		// Date relDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		// .parse("30-"+date);
		// SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		// String date = df.format(new Date());
		List rateList = rateHistoryDAO.fetchRates(srcCty, service, new Date());
		dataMap.put("Rate_data", rateList);
		CountryInfo ctyInfo = countryInfoDAO
				.findCOuntryInfoByCountryName(srcCty);
		ServiceInfo serviceInfo = serviceInfoDAO
				.findServiceInoByCountryService(service, ctyInfo);
		dataMap.put("pecktime", serviceInfo.getPeakStartTime());
		dataMap.put("offpecktime", serviceInfo.getOffpeakStartTime());
		PdfUtils.generateRateSheet(fileName, dataMap);
		System.out.println("path=" + ExcelUtils.getOutPutDir(date));
		return ExcelUtils.getOutPutDir(date) + "\\" + fileName + ".pdf";
	}

	@Override
	@Transactional
	public String createRateSheet(String fileName) throws Exception {
		Map dataMap = new HashMap();
		String date = fileName.split("_")[3];
		String service = fileName.split("_")[1];
		String srcCty = fileName.split("_")[2];
		// Date relDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		// .parse(new Date());

		// SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		// String date = df.format(new Date());

		List rateList = rateHistoryDAO.fetchRates(srcCty, service, new Date());
		dataMap.put(service + "_" + srcCty, rateList);
		ExcelUtils
				.generateExcelFile(fileName, ExcelUtils.RATES_HEADER, dataMap);
		System.out.println("path =" + ExcelUtils.getOutPutDir(date));
		return ExcelUtils.getOutPutDir(date) + "\\" + fileName + ".xls";
	}

	@Override
	@Transactional
	public void processTrafficBatch(String fileName) throws ParseException {
		String month = fileName.split("_")[3];
		Date endDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		.parse("30-" + month);

		Date startDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		.parse("01-" + month);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		trafficSummaryDAO.processMonthlyTrafic(df.format(startDate), df.format(endDate));;
	}
	
	
	@Override
	@Transactional
	public String generateTrafficSummary(String fileName) throws Exception {
		Map dataMap = new HashMap();
		String month = fileName.split("_")[3];
		List traffics = trafficSummaryDAO.getMonthlyTraffics();
		dataMap.put("Traffic_Summary" + month, traffics);
		ExcelUtils.generateExcelFile(fileName,
				ExcelUtils.TRAFFIC_SUMMARY_HEADER, dataMap);
		System.out.println("path =" + ExcelUtils.getOutPutDir(month));
		return ExcelUtils.getOutPutDir(month) + "\\" + fileName + ".xls";
	}

	@Override
	@Transactional
	public boolean readCallFile(File callFile) throws Exception {
		List<CallDetail> callData = ExcelUtils.readExcelFile(callFile,
				ExcelUtils.CALL_FILE_TYPE);
		callDetailDAO.importCallDetail(callData);
		return true;
	}

	@Override
	@Transactional
	public boolean readRateFile(File rateFile) throws Exception {
		List<RateHistoryTemp> rates = ExcelUtils.readExcelFile(rateFile,
				ExcelUtils.RATES_FILE_TYPE);
		rateHistoryTempDAO.importRates(rates);
		RatesUpdateHistory history = updateRateHistory(rateFile);
		Map serviceMap = new HashMap();
		for (int i = 0; i < rates.size(); i++) {
			RateHistoryTemp rate = rates.get(i);
			String srcCty = rate.getSrcCountry();
			String service = rate.getServviceType();
			String key = srcCty + "_" + service;
			if (!serviceMap.containsKey("key")) {
				ServiceInfo serviceInfo = new ServiceInfo();
				serviceInfo.setServviceType(service);
				serviceInfo.setUpdateHistoryID(history.getId().toString());
				serviceInfo.setPeakStartTime("1000");
				serviceInfo.setOffpeakStartTime("2200");
				CountryInfo ctyInfo = countryInfoDAO
						.findCOuntryInfoByCountryName(srcCty);
				serviceInfo.setCountryInfo(ctyInfo);
				serviceMap.put(key, serviceInfo);
			}
		}
		if (!serviceMap.isEmpty()) {
			updateServiceInfo(serviceMap);
		}

		return true;
	}

	private void updateServiceInfo(Map serviceMap) {
		Iterator it = serviceMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry paris = (Entry) it.next();
			ServiceInfo ser = (ServiceInfo) paris.getValue();
			serviceInfoDAO.save(ser);
		}

	}

	private RatesUpdateHistory updateRateHistory(File rateFile)
			throws ParseException {
		RatesUpdateHistory history = new RatesUpdateHistory();
		String partName = rateFile.getName().split("_")[1];
		Date date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
				.parse(partName.substring(0, partName.length() - 4));
		history.setFileName(rateFile.getName());
		history.setEffectDate(date);
		history.setUpdateTime(new Date());
		return ratesUpdateHistoryDAO.save(history);
	}

	@Override
	@Transactional
	public void processRateUpdate() {
		rateHistoryTempDAO.processRateUpdate();
	}


	
	@Override
	@Transactional
	public void processCommissionBatch(String fileName) throws ParseException {
		String month = fileName.split("_")[3];
		Date endDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		.parse("30-" + month);

		Date startDate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		.parse("01-" + month);
		commissionDAO.processCommissionBatch(startDate.toLocaleString(), endDate.toLocaleString());;
	}
	
	@Override
	@Transactional
	public String generateMonthCommissions(String fileName) throws Exception {
		Map dataMap = new HashMap();
		String month = fileName.split("_")[3];
		List traffics = commissionDAO.getMonthlyCommission();
		dataMap.put("Monthly_Commission" + month, traffics);
		ExcelUtils.generateExcelFile(fileName,
				ExcelUtils.COMMISSION, dataMap);
		System.out.println("path =" + ExcelUtils.getOutPutDir(month));
		return ExcelUtils.getOutPutDir(month) + "\\" + fileName + ".xls";

	}

}
