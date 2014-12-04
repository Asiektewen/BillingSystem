/**
 * 
 */
package com.telecom.billing.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
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
import org.springframework.web.context.request.WebRequest;

import com.telecom.billing.services.FileService;
import com.telecom.billing.services.UserService;

/**
 * @author zhangle
 *
 */
@Controller
@RequestMapping("/admin/billing")
// @SessionAttributes({ "user" })
public class BillingController {
	private static final Logger logger = LoggerFactory
			.getLogger(BillingController.class);

	@ModelAttribute
	public void currentPage(WebRequest request, Model model) {
		model.addAttribute("currentPage", "billing");
	}

	@Autowired
	public FileService fileService;
	@Autowired
	@Qualifier("userService")
	public UserService userService;

	// @RequestMapping(value = "/json", method = RequestMethod.POST)
	// public @ResponseBody String readJson(@Valid @RequestBody JavaBean bean) {
	// return "Read from JSON: " + bean;
	// }

	@RequestMapping(value = { "/gen", "/gen/" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> genMonthlyBill(
			@RequestParam String month, Model model, HttpServletRequest request)
			throws Exception {
		logger.debug("Generate Bills:Month is " + month);
		fileService.processBillBatch("Bill_" + month);

		String result = fileService.generateMonthlyBills("Bill_" + month);
//		String result = "D:\\tmp\\output\\01-2014";
		File file = new File(result);
		File[] files = file.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return !f.isDirectory();
			}
		});
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();
//		int len = files.length;
		for (File temp : files) {
			String pathMd5 = "";
			if (temp != null && temp.getAbsolutePath().indexOf(".pdf") != -1) {
				pathMd5 = DigestUtils.md5Hex(temp.getAbsolutePath().getBytes(
						"UTF-8"));
				request.getSession().setAttribute(pathMd5,
						temp.getAbsolutePath());
				HashMap<String, String> mapt = new HashMap<String, String>();
				mapt.put("path", pathMd5);
				mapt.put("name", temp.getName());
				fileList.add(mapt);
			}
		}

		String name = "Bill_" + month;
		// String pathMd5 = "";
		// if (result != null && !result.equalsIgnoreCase("")) {
		// pathMd5 = DigestUtils.md5Hex(result.getBytes("UTF-8"));
		// request.getSession().setAttribute(pathMd5, result);
		// }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("content", "Bills are generated into Foler: ");
		map.put("folderName", result);
		map.put("fileList", fileList);
		// map.put("fileName", name);
		return map;
	}
}
