/**
 * 
 */
package com.telecom.billing.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.model.ServiceInfo;
import com.telecom.billing.services.ServiceInfoService;
import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Controller
@SessionAttributes({ "user" })
@RequestMapping("/admin/rates/")
public class RatesController {
	private static final Logger logger = LoggerFactory
			.getLogger(RatesController.class);
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	@Autowired
	@Qualifier("serviceInfoService")
	public ServiceInfoService serviceInfoService;

	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "rates");
	}

	@RequestMapping(value = { "/updateRates", "/updateRates/" }, method = RequestMethod.GET)
	public String updateRates(HttpServletRequest request, Model model) {
		String message = (String) request.getSession().getAttribute(
				"uploadMessage");
		model.addAttribute("uploadMessage", message);
		return "admin/updateRates";

	}

	@ModelAttribute("serviceInfo")
	public ServiceInfo createFormBean() {
		return new ServiceInfo();
	}

	@RequestMapping(value = { "/expSheet", "/expSheet/" }, method = RequestMethod.GET)
	public String expSheet(Model model) throws JsonGenerationException,
			JsonMappingException, IOException {
		List<ServiceInfo> serviceInfoList = serviceInfoService.findAll();
		// put serviceInfos into groups according to serviceType;
		Map<String, ArrayList<ServiceInfo>> map = new HashMap<String, ArrayList<ServiceInfo>>();
		Iterator<ServiceInfo> i = serviceInfoList.iterator();
		while (i.hasNext()) {
			ServiceInfo si = i.next();
			ArrayList<ServiceInfo> list = map.get(si.getServviceType());
			if (list == null) {
				list = new ArrayList<ServiceInfo>();
			}
			list.add(si);
			map.put(si.getServviceType(), (ArrayList<ServiceInfo>) list);

		}
		Iterator<String> mi = map.keySet().iterator();
		while (mi.hasNext()) {
			ArrayList<ServiceInfo> infoList = map.get(mi.next());
			Collections.sort(infoList, new Comparator<ServiceInfo>() {
				public int compare(ServiceInfo o1, ServiceInfo o2) {
					return o1.getCountryInfo().getCountryCode()
							.compareTo(o2.getCountryInfo().getCountryCode());
				}
			});
		}

		List<String> keys = new ArrayList<String>(map.keySet());
		// Sorting
		Collections.sort(keys);

		model.addAttribute("serviceKeys", keys);
		model.addAttribute("serviceKeysJSON",
				new ObjectMapper().writeValueAsString(keys));
		model.addAttribute("serviceInfoJSON",
				new ObjectMapper().writeValueAsString(map));
		model.addAttribute("serviceInfoListMap", map);
		model.addAttribute("serviceInfoList", serviceInfoList);
		return "admin/exportRatesSheet";

	}

	@RequestMapping(value = { "/expCurrent", "/expCurrent/" }, method = RequestMethod.GET)
	public String expCurrent() {
		return "admin/exportCurrentRates";

	}

	@RequestMapping(value = { "/expTraffic", "/expTraffic/" }, method = RequestMethod.GET)
	public String expTraffic() {
		return "admin/exportTrafficSummary";

	}

	@RequestMapping(value = { "/traffic/gen", "/traffic/gen/" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> genTraffic(
			@RequestParam String month, Model model) {
		logger.debug("Generate Bills:Month is " + month);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		map.put("content", month);
		return map;

	}

	@RequestMapping(value = { "/expRateSheet", "/expRateSheet/" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> expRateSheet(
			@RequestParam String serviceType, @RequestParam String countryName,
			@RequestParam String countryCode, Model model) {
		logger.debug("Export Rate Sheet: @serviceType =" + serviceType
				+ " @CountryCode=" + countryCode);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		map.put("content", "Export Rate Sheet for " + serviceType + "_"
				+ countryName + "_" + countryCode);
		return map;

	}
}
