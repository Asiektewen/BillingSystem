/**
 * 
 */
package com.telecom.billing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.telecom.billing.controller.WaiverController;
import com.telecom.billing.services.WaiverService;

/**
 * @author zhangle
 *
 */
public class WaiverControllerTest extends ControllerTestBase {
	@InjectMocks
	private WaiverController waiverController;
	@Mock
	private WaiverService waiverService;

	protected void buildMockMvc() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(waiverController)
				.build();
	}

	@Test
	public void testSetStatus() throws Exception {
		when(waiverService.findOne(Long.valueOf("1"))).thenReturn(
				createOneNewWaiver());
		// Model model = new ExtendedModelMap();
		mockMvc.perform(get("/waivers/status/approve/1"))
		// .andExpect(status().isOk())
				// .andExpect(content().contentType("application/json"))
				// .andExpect(view().name(containsString("view/adf")));
				// .andExpect(model().attribute("foo", "bar"))
				.andExpect(redirectedUrlPattern("/waivers/list/**"));
	}
}
