package com.telecom.billing.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.telecom.billing.Utils.ExcelUtils;
import com.telecom.billing.services.FileService;
import com.telecom.billing.util.AjaxUtils;

@Controller
@RequestMapping("/admin/file")
public class FileUploadController {
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
	public String storeFilePath = ExcelUtils.getInPutDir();

	@Autowired
	public FileService fileService;

	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}

	@RequestMapping(method = RequestMethod.GET)
	public void fileUploadForm() {
	}

	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String processUpload(@RequestParam MultipartFile file,
			String function, String param, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		File result = saveFile(file);
		if (result != null) {
			request.getSession().setAttribute(
					"uploadMessage",
					"File '" + file.getOriginalFilename()
							+ "' uploaded successfully");
			if (function.equalsIgnoreCase("updateRates")) {
				fileService.readRateFile(result);
			} else {
				fileService.readCallFile(result);

			}
		} else {
			model.addAttribute("message", "File '" + file.getOriginalFilename()
					+ "' uploaded unsuccessfully");
		}
		StringBuilder sb = new StringBuilder();
		sb.append(request.getContextPath());
		if (function.equalsIgnoreCase("updateRates")) {
			sb.append("/admin/rates/updateRates");
		} else if (function.equalsIgnoreCase("processCallDetails")) {
			sb.append("/admin/callDetails/upload");
		} else {
			sb.append("/admin/");
		}
		response.sendRedirect(sb.toString());
		return null;
	}

	public File saveFile(MultipartFile file) {
		String name = file.getOriginalFilename();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = storeFilePath;
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				// return "You successfully uploaded file=" + name;
				return serverFile;
			} catch (Exception e) {
				// return "You failed to upload " + name + " => " +
				// e.getMessage();
				return null;
			}
		} else {
			// return "You failed to upload " + name
			// + " because the file was empty.";
			return null;
		}

	}
}
