/**
 * 
 */
package com.telecom.billing.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.services.FileService;
import com.telecom.billing.services.ServiceInfoService;
import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Controller
@SessionAttributes({ "user" })
@RequestMapping("/admin/salesrep/")
public class SalesRepController {
	private static final Logger logger = LoggerFactory
			.getLogger(SalesRepController.class);
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	@Autowired
	@Qualifier("serviceInfoService")
	public ServiceInfoService serviceInfoService;
	@Autowired
	public FileService fileService;

	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "salesrep");
	}

	@RequestMapping(value = { "/exportCommission", "/exportCommission/" }, method = RequestMethod.GET)
	public String expCommission(HttpServletRequest request, Model model) {
		return "admin/exportCommission";

	}

	@RequestMapping(value = { "/exportCommission", "/exportCommission/" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, String> expCommissionFile(
			HttpServletRequest request, Model model, @RequestParam String month)
			throws Exception {
		logger.debug("Export Commission for month =" + month);
		String name = "monthly_commission_of_" + month;
		String result = fileService.createRateSheet(name);
		String pathMd5 = "";
		if (result != null && !result.equalsIgnoreCase("")) {
			pathMd5 = DigestUtils.md5Hex(result.getBytes("UTF-8"));
			request.getSession().setAttribute(pathMd5, result);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "success");
		map.put("content", "Export Commission for " + month);
		map.put("file", pathMd5);
		map.put("fileName", name);
		return map;

	}

}
