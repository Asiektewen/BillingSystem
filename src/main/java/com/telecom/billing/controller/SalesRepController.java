/**
 * 
 */
package com.telecom.billing.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.common.SysConstant;
import com.telecom.billing.model.User;
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

	@ModelAttribute("salesRep")
	public User createFormBean() {
		return new User();
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
		fileService.processCommissionBatch(name);
		String result = fileService.generateMonthCommissions(name);
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

	@RequestMapping(value = { "/create", "/create/" }, method = RequestMethod.GET)
	public String createSalesRep(Model model, HttpSession session)
			throws JsonGenerationException, JsonMappingException, IOException {
		if (session.getAttribute("createSalesRepMsg") != null) {
			model.addAttribute("createSalesRepMsg",
					session.getAttribute("createSalesRepMsg"));
		}
		return "admin/createSalesRep";
	}

	@RequestMapping(value = { "/list", "/list/" }, method = RequestMethod.GET)
	public String listSalesRep(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "joinDate") String orderBy,
			@RequestParam(value = "orderType", defaultValue = "desc") String orderType,
			Model model) throws JsonGenerationException, JsonMappingException,
			IOException {
		Integer start = (page - 1) * size + 1;

		List<User> userList = null;
		Integer totalPage = 0;
		Integer totalCount = 0;
		userList = userService.findUsersWithoutAdmin(start, size, orderBy,
				orderType);

		totalCount = userService.countAllUserNotAdmin();
		totalPage = (totalCount / size) + 1;

		Map<String, Object> map = new HashMap<String, Object>();
		int n = page / 5;
		int n2 = totalPage / 5;
		n2 = n2 <= n + 1 ? n : n + 1;
		int mod = page % 5;
		int mod2 = totalPage % 5;
		mod2 = n2 == n ? mod2 : 5;
		int[] pageRage = new int[mod2];
		for (int i = 0; i < mod2; i++) {
			pageRage[i] = n2 * 5 + i + 1;
		}
		map.put("page", page);
		map.put("pageRange", pageRage);
		map.put("size", size);
		map.put("orderBy", orderBy);
		map.put("orderType", orderType);
		map.put("total", totalCount);
		map.put("totalPage", totalPage);
		model.addAttribute("userList", userList);
		model.addAttribute("customerListPageInfo", map);
		return "admin/userList";
	}

	@RequestMapping(value = { "/save", "/save/" }, method = RequestMethod.POST)
	public String saveRep(@ModelAttribute("salesRep") User salesRep,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String submit = request.getParameter("submit");
		// String reset = request.getParameter("reset");
		if (submit != null) {
			// save
			salesRep.setJoinDate(new Date());
			salesRep.setRole(SysConstant.ROLE_SALESREP);
			userService.save(salesRep);
			request.getSession().setAttribute("createSalesRepMsg",
					"Successfully!");
		} else {
			// reset
			request.getSession().setAttribute("salesRep", new User());

		}
		StringBuilder sb = new StringBuilder();
		sb.append(request.getContextPath());
		sb.append("/admin/salesrep/create/");
		response.sendRedirect(sb.toString());
		return null;

	}

}
