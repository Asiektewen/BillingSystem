/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.CountryInfoDAO;
import com.telecom.billing.model.CountryInfo;

/**
 * @author zhangle
 *
 */
@Repository("countryInfoDAO")
public class CountryInfoDAOImpl extends GenericDAOImpl<CountryInfo> implements
		CountryInfoDAO {

	@Override
	public CountryInfo findCountryInfoByCountryNum(String countryNum) {
		String sql = "FROM CountryInfo  c WHERE c.countryNum= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, countryNum);
		List<CountryInfo> results = query.list();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public CountryInfo findCOuntryInfoByCountryName(String countryName) {
		String sql = "FROM CountryInfo  c WHERE c.countryNum= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, countryName);
		List<CountryInfo> results = query.list();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

}
