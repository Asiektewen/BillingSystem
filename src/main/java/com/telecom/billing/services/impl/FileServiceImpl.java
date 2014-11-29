/**
 * 
 */
package com.telecom.billing.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.telecom.billing.Utils.PdfUtils;
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
	public List<CallDetail> readCallFile(File callFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RateHistory> readRateFile(File rateFile) {
		// TODO Auto-generated method stub
		return null;
	}

}
