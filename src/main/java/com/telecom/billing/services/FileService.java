/**
 * 
 */
package com.telecom.billing.services;

import java.io.File;
import java.util.List;

import com.telecom.billing.model.CallDetail;
import com.telecom.billing.model.RateHistory;


/**
 * @author Eric
 *
 */
public interface FileService {
	
	/**
	 *  file format: pdf
	 * @param fileName (format: Bill_srcPhoneNo_date)
	 * @return full path of the file
	 * @throws Exception 
	 */
	
	public String generateMonthlyBill(String fileName) throws Exception;
	/**
	 * file format : pdf
	 * @param fileName (format: Rate_service_srccty_date )
	 * @return full path of the file
	 */

	public String generateRateSheet(String fileName);
	
	/**
	 * file format : single sheet Excel
	 * @param fileName (format: TrafficSummary_201411 )
	 * @return full path of the file
	 */
	public String generateTrafficSummary(String fileName);
	
	/**
	 * file format : multiple sheets Excel
	 * @param fileName (format: Rates_201411)
	 * @return full path of the file
	 */
	public String generateRateSheets(String fileName);
	
	/**
	 * @param callFile:File in excel format
	 * @return CallDetail list;
	 * @throws Exception 
	 */
	public boolean readCallFile(File callFile) throws Exception;
	
	/**
	 * 
	 * @param rateFile
	 * @return RateHistory list
	 * @throws Exception 
	 */
	
	public boolean readRateFile(File rateFile) throws Exception;
}
