/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.Bill;

/**
 * @author QZhang
 * 
 */
public interface BillDAO extends GenericDAO<Bill> {
	public void generateMonthlyBill(String startTime, String endTime);
	public List getBillListbySrcPhone(String phoneNo);
	public void cleanTable();

}
