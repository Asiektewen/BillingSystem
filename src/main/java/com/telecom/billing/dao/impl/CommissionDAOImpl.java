package com.telecom.billing.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.CommissionDAO;
import com.telecom.billing.model.Commission;

@Repository("commissionDAO")
public class CommissionDAOImpl extends GenericDAOImpl<Commission> implements
		CommissionDAO {

	@Override
	public List<Commission> getMonthlyCommission(String month) {
		List co = new ArrayList();
		for (int i = 0; i < 50; i++) {
			Commission com= new Commission();
			com.setSalsRep("sales"+i);
			com.setTotalCommission("100"+i);
			co.add(com);
		}

		return co;
	}

}
