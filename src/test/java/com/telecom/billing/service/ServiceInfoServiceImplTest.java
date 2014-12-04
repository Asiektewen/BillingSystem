/**
 * 
 */
package com.telecom.billing.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.billing.model.ServiceInfo;

/**
 * @author zhangle
 *
 */
public class ServiceInfoServiceImplTest extends ServiceImplTestBase {
	@Test
	@Transactional
	public void testSaveUser() {
		ServiceInfo serviceInfo = serviceInfoService
				.findServiceInoByCountryService("Premium", "USA");
		assertNotNull(serviceInfo);
	}
}
