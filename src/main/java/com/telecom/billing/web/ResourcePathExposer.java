/**
 * 
 */
package com.telecom.billing.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * @author zhangle
 *
 */
public class ResourcePathExposer implements ServletContextAware {
	private ServletContext application;
	private String adminResRoot = "";

	/*
	 * 初始化方法
	 */
	public void init() {
		adminResRoot = "/billingAdmin/resources/admin";
		getServletContext().setAttribute("adminResRoot", adminResRoot);
		getServletContext().setAttribute("contextPath",
				getServletContext().getContextPath());
	}

	public String getAdminResRoot() {
		return adminResRoot;
	}

	public void setAdminResRoot(String adminResRoot) {
		this.adminResRoot = adminResRoot;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		application = servletContext;

	}

	public ServletContext getServletContext() {
		return this.application;
	}

}
