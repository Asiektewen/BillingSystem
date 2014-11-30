/**
 * 
 */
package com.telecom.billing.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.telecom.billing.Utils.ExcelUtils;
import com.telecom.billing.Utils.PdfUtils;
import com.telecom.billing.dao.CallDetailDAO;
import com.telecom.billing.dao.RateHistoryDAO;
import com.telecom.billing.model.Bill;
import com.telecom.billing.model.CallDetail;
import com.telecom.billing.model.RateHistory;
import com.telecom.billing.services.FileService;

/**
 * @author Eric
 *
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Autowired
	@Qualifier("rateHistoryDAO")
	public RateHistoryDAO rateHistoryDAO;
	
	@Autowired
	@Qualifier("callDetailDAO")
	public CallDetailDAO callDetailDAO;
	
	@Override
	public String generateMonthlyBill(String fileName) throws Exception {
		// TODO Auto-generated method stub
		Map billMap = new HashMap();
		List callList = new ArrayList();
		for (int i = 0; i < 60; i++) {
			Bill bill = new Bill();
		    bill.setDestPhoneNo("000000000"+i);
		    bill.setDestCtyName("cty"+i);
		    bill.setCallTime("02120"+i);
		    bill.setDuration(i+1);
		    bill.setCostOfCall((i+1)+10);
			callList.add(bill);
		}
		billMap.put("Bill_data", callList);
		billMap.put("due_amt", "$"+38200);
		return PdfUtils.generateMonthlyBill(fileName, billMap);
	}

	@Override
	public String generateRateSheet(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateTrafficSummary(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateRateSheets(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean readCallFile(File callFile) throws Exception {
		List<CallDetail> callData = ExcelUtils.readExcelFile(callFile, ExcelUtils.CALL_FILE_TYPE);
		callDetailDAO.importCallDetail(callData);
		return true;
	}

	@Override
	public boolean readRateFile(File rateFile) throws Exception {
		List<RateHistory> rates =  ExcelUtils.readExcelFile(rateFile, ExcelUtils.RATES_FILE_TYPE);
		rateHistoryDAO.importRates(rates);
		return true;
	}

}
