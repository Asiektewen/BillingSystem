/**
 * 
 */
package com.telecom.billing.services;

import com.telecom.billing.model.CountryInfo;

/**
 * @author zhangle
 *
 */
public interface CountryInfoService extends GenericService<CountryInfo> {
	public CountryInfo findCountryInfoByCountryNum(String countryNum);

	public CountryInfo findCOuntryInfoByCountryName(String countryName);
}
