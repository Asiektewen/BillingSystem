/**
 * 
 */
package com.telecom.billing.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.telecom.billing.dao.CountryInfoDAO;
import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.model.CountryInfo;
import com.telecom.billing.services.CountryInfoService;

/**
 * @author zhangle
 *
 */
@Service("countryInfoService")
public class CountryInfoServiceImpl extends GenericServiceImpl<CountryInfo>
		implements CountryInfoService {
	@Autowired
	@Qualifier("countryInfoDAO")
	public CountryInfoDAO countryInfoDAO;

	@Override
	protected GenericDAO<CountryInfo> getDAO() {
		return countryInfoDAO;
	}

	@Override
	public CountryInfo findCountryInfoByCountryNum(String countryNum) {
		return countryInfoDAO.findCountryInfoByCountryNum(countryNum);
	}

	@Override
	public CountryInfo findCOuntryInfoByCountryName(String countryName) {
		return countryInfoDAO.findCOuntryInfoByCountryName(countryName);
	}

}
