/**
 * 
 */
package com.telecom.billing.dao;

import java.util.List;

import com.telecom.billing.model.CountryInfo;
import com.telecom.billing.model.ServiceInfo;

/**
 * @author zhangle
 *
 */
public interface ServiceInfoDAO extends GenericDAO<ServiceInfo> {
	public List<ServiceInfo> findServiceInoByCountry(String CountryNum);

	public List<ServiceInfo> findServcieInfoByServiceType(String serviceType);

	public ServiceInfo findServiceInoByCountryService(String serviceType,
			String countryName);

}
