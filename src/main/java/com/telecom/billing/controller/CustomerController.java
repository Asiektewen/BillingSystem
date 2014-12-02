package com.telecom.billing.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.common.SysConstant;
import com.telecom.billing.model.Customer;
import com.telecom.billing.model.ServiceInfo;
import com.telecom.billing.model.User;
import com.telecom.billing.services.CustomerService;
import com.telecom.billing.services.ServiceInfoService;
import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Controller
@SessionAttributes({ "user" })
@RequestMapping("/admin/customer")
public class CustomerController {
	private static final Logger logger = LoggerFactory
			.getLogger(CustomerController.class);
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	@Autowired
	@Qualifier("customerService")
	public CustomerService customerService;
	@Autowired
	@Qualifier("serviceInfoService")
	public ServiceInfoService serviceInfoService;

	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "customer");
	}

	@RequestMapping(value = { "/list", "/list/" }, method = RequestMethod.GET)
	public String listCustomer(
			@ModelAttribute User user,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "joinDate") String orderBy,
			@RequestParam(value = "orderType", defaultValue = "desc") String orderType,
			Model model) {
		Integer start = (page - 1) * size + 1;

		List<Customer> customerList = null;
		Integer totalPage = 0;
		Integer totalCount = 0;
		if (SysConstant.ROLE_ADMIN.equalsIgnoreCase(user.getRole())) {
			customerList = customerService.findAllCustomerByUser(start - 1,
					size, orderBy, orderType, user.getId(), 2);
			totalCount = customerService.countAllCustomerByAdmin();

		} else if (SysConstant.ROLE_SALESREP.equalsIgnoreCase(user.getRole())) {
			customerList = customerService.findAllCustomerByUser(start, size,
					orderBy, orderType, user.getId(), 1);
			totalCount = customerService.countAllCustomerByUser(user.getId());
		} else {
			customerList = new ArrayList<Customer>();
		}
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
		model.addAttribute("customerList", customerList);
		model.addAttribute("customerListPageInfo", map);
		return "admin/customerList";

	}

	@RequestMapping(value = { "/genBill", "/genBill/" }, method = RequestMethod.GET)
	public String genBill() {
		return "admin/generateBill";

	}

	@RequestMapping(value = { "/create", "/create/" }, method = RequestMethod.GET)
	public String createCustomer(Model model, HttpSession session)
			throws JsonGenerationException, JsonMappingException, IOException {
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
		if (session.getAttribute("createCustomerMsg") != null) {
			model.addAttribute("createCustomerMsg",
					session.getAttribute("createCustomerMsg"));
		}
		model.addAttribute("serviceKeys", keys);
		model.addAttribute("serviceKeysJSON",
				new ObjectMapper().writeValueAsString(keys));
		model.addAttribute("serviceInfoJSON",
				new ObjectMapper().writeValueAsString(map));
		model.addAttribute("currentPage", "customer");
		return "admin/createCustomer";

	}

	@ModelAttribute("customer")
	public Customer createFormBean() {
		return new Customer();
	}

	@RequestMapping(value = { "/save", "/save/" }, method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute User user,
			@ModelAttribute("customer") Customer customer,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String submit = request.getParameter("submit");
		// String reset = request.getParameter("reset");
		if (submit != null) {
			// save
			customer.setSalesRepID(user.getId());
			customer.setJoinDate(new Date());
			customerService.save(customer);
			request.getSession().setAttribute("createCustomerMsg",
					"Successfully!");
		} else {
			// reset
			request.getSession().setAttribute("customer", new Customer());

		}
		StringBuilder sb = new StringBuilder();
		sb.append(request.getContextPath());
		sb.append("/admin/customer/create/");
		response.sendRedirect(sb.toString());
		return null;
		// return "redict:admin/createCustomer";

	}
}
