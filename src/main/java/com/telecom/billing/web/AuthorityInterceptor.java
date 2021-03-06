/**
 * 
 */
package com.telecom.billing.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.telecom.billing.model.User;

/**
 * @author zhangle
 *
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handler2 = (HandlerMethod) handler;
		FireAuthority fireAuthority = handler2
				.getMethodAnnotation(FireAuthority.class);

		if (null == fireAuthority) {
			return true;
		} else {
			HttpSession session = request.getSession();
			if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				if (user.isStatus()) {
					return true;
				} else {
					StringBuilder sb = new StringBuilder();
					sb.append(request.getContextPath());
					sb.append("/login/");
					response.sendRedirect(sb.toString());
					return false;
				}
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(request.getContextPath());
				sb.append("/login/");
				response.sendRedirect(sb.toString());
				return false;

			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
