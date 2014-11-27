/**
 * 
 */
package com.telecom.billing.dao;

import com.telecom.billing.model.CountryInfo;

/**
 * @author zhangle
 *
 */
public interface CountryInfoDAO extends GenericDAO<CountryInfo> {
	public CountryInfo findCountryInfoByCountryNum(String countryNum);

	public CountryInfo findCOuntryInfoByCountryName(String countryName);
}
