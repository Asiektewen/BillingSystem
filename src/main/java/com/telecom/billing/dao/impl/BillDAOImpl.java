/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.BillDAO;
import com.telecom.billing.model.Bill;

/**
 * @author QZhang
 * 
 */
@Repository("billDAO")
public class BillDAOImpl extends GenericDAOImpl<Bill> implements BillDAO {

	@Override
	public void generateMonthlyBill(String startTime, String endTime) {
		Query query = this
				.getCurrentSession()
				.createSQLQuery(
						"{call get_month_cost_detail(:startTime,:endTime)}")
				.setParameter("startTime", startTime)
				.setParameter("endTime", endTime);
		List result = query.list();

	}

	@Override
	public List<Bill> getBillListbySrcPhone(String phoneNo) {
		Query query = getCurrentSession().createQuery(
				"from Bill c where c.srcPhoneNo= ? ");
		query.setParameter(0, phoneNo);
		return query.list();
	}

	@Override
	public void cleanTable() {
		//getCurrentSession()
		
	}

}
