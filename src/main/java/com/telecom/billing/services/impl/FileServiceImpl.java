/**
 * 
 */
package com.telecom.billing.services.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.Utils.ExcelUtils;
import com.telecom.billing.Utils.PdfUtils;
import com.telecom.billing.dao.CallDetailDAO;
import com.telecom.billing.dao.CustomerDAO;
import com.telecom.billing.dao.RateHistoryDAO;
import com.telecom.billing.dao.RateHistoryTempDAO;
import com.telecom.billing.model.CallDetail;
import com.telecom.billing.model.Customer;
import com.telecom.billing.model.RateHistoryTemp;
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

	@Override
	@Transactional
	public String generateMonthlyBills(String month) throws Exception {
		List customers = customerDAO.findAllCustomer();
		for (int i = 0; i < customers.size(); i++) {
			Customer customer = (Customer) customers.get(i);
			String phoneNO = customer.getPhoneNumber();
			Map billMap = new HashMap();
			// TODO:
			billMap.put("Bill_data", "");
			PdfUtils.generateMonthlyBill("Bill_" + phoneNO + "_" + month,
					billMap);
		}
		return ExcelUtils.getOutPutDir() + "\\" + month;
	}

	@Override
	@Transactional
	public String generateRateSheet(String fileName) throws Exception {
		Map dataMap = new HashMap();
		String date = fileName.split("_")[3];
		String service = fileName.split("_")[1];
		String srcCty = fileName.split("_")[2];
		Date relDate =  new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		.parse("30-"+date);
		List rateList = rateHistoryDAO.fetchRates(srcCty, service,relDate);
		dataMap.put("Rate_data", rateList);
		PdfUtils.generateRateSheet(fileName, dataMap);
		return ExcelUtils.getOutPutDir() + "\\" + date;
	}

	@Override
	@Transactional
	public String createRateSheet(String fileName) throws Exception {
		Map dataMap = new HashMap();
		String date = fileName.split("_")[3];
		String service = fileName.split("_")[1];
		String srcCty = fileName.split("_")[2];
		Date relDate =  new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
		.parse("30-"+date);
		List rateList = rateHistoryDAO.fetchRates(srcCty, service,relDate);
		dataMap.put(service+"_"+srcCty, rateList);
		ExcelUtils.generateExcelFile(fileName, ExcelUtils.RATES_HEADER, dataMap);
		return ExcelUtils.getOutPutDir() + "\\excel\\" + date;
	}

	@Override
	public String generateTrafficSummary(String fileName) {
		// TODO Auto-generated method stub
		return null;
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
		return true;
	}

	@Override
	@Transactional
	public void processRateUpdate() {
		rateHistoryTempDAO.processRateUpdate();
	}

}
