/**
 * 
 */
package com.telecom.billing.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.telecom.billing.dao.GenericDAO;
import com.telecom.billing.dao.ServiceInfoDAO;
import com.telecom.billing.model.ServiceInfo;
import com.telecom.billing.services.ServiceInfoService;

/**
 * @author zhangle
 *
 */
@Service("serviceInfoService")
public class ServiceInfoServiceImpl extends GenericServiceImpl<ServiceInfo>
		implements ServiceInfoService {
	@Autowired
	@Qualifier("serviceInfoDAO")
	public ServiceInfoDAO serviceInfoDAO;

	@Override
	protected GenericDAO<ServiceInfo> getDAO() {
		return serviceInfoDAO;
	}

	@Override
	public List<ServiceInfo> findServiceInoByCountry(String CountryNum) {
		return serviceInfoDAO.findServiceInoByCountry(CountryNum);
	}

	@Override
	public List<ServiceInfo> findServcieInfoByServiceType(String serviceType) {
		return serviceInfoDAO.findServcieInfoByServiceType(serviceType);
	}

}
