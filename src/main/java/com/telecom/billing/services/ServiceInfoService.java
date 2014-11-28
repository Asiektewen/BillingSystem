/**
 * 
 */
package com.telecom.billing.services;

import java.util.List;

import com.telecom.billing.model.ServiceInfo;

/**
 * @author zhangle
 *
 */
public interface ServiceInfoService extends GenericService<ServiceInfo> {
	public List<ServiceInfo> findServiceInoByCountry(String CountryNum);

	public List<ServiceInfo> findServcieInfoByServiceType(String serviceType);
}
