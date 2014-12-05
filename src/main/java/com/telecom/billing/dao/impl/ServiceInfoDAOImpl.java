/**
 * 
 */
package com.telecom.billing.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.telecom.billing.dao.ServiceInfoDAO;
import com.telecom.billing.model.ServiceInfo;

/**
 * @author zhangle
 *
 */
@Repository("serviceInfoDAO")
public class ServiceInfoDAOImpl extends GenericDAOImpl<ServiceInfo> implements
		ServiceInfoDAO {

	@Override
	public List<ServiceInfo> findServiceInoByCountry(String countryNum) {
		String sql = "FROM ServiceInfo  s WHERE s.countryNum= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, countryNum);
		List<ServiceInfo> results = query.list();
		if (results != null && results.size() > 0) {
			return results;
		}
		return null;
	}

	@Override
	public List<ServiceInfo> findServcieInfoByServiceType(String serviceType) {
		String sql = "FROM ServiceInfo  s WHERE s.serviceType= ? ";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, serviceType);
		List<ServiceInfo> results = query.list();
		if (results != null && results.size() > 0) {
			return results;
		}
		return null;
	}

	@Override
	public ServiceInfo findServiceInoByCountryService(String serviceType,
			String countryName) {
		String sql = "FROM ServiceInfo  s WHERE s.serviceType= ? and s.countryInfo.countryName= ?";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, serviceType);
		query.setParameter(1, countryName);
		List<ServiceInfo> results = query.list();
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

}
