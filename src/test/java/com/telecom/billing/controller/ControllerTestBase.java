/**
 * 
 */
package com.telecom.billing.controller;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.telecom.billing.test.TestBase;

/**
 * @author zhangle
 *
 */

@WebAppConfiguration
public class ControllerTestBase extends TestBase {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		buildMockMvc();
	}

	protected void buildMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(
				this.webApplicationContext).build();
	}

}
