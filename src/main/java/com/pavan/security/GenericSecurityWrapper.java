package com.pavan.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenericSecurityWrapper implements Filter {

	protected String httpHeaderFile;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String propFile = filterConfig.getInitParameter("httpHeaderFile");
		if (propFile != null) {
			httpHeaderFile = propFile;
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		final StringBuffer url = ((HttpServletRequest) request).getRequestURL();
		if (!url.toString().contains("styles.css?") && !url.toString().endsWith("css.gz") && !url.toString().endsWith("js.gz")
				&& ("POST".equals(req.getMethod()) || "GET".equals(req.getMethod()) || "PUT".equals(req.getMethod()))) {
			res.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
			res.addHeader("X-XSS-Protection", "1; mode=block");
			res.addHeader("X-Content-Type-Options", "nosniff");
			res.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			res.addHeader("Pragma", "no-cache");
			res.addHeader("Expires", "0");
			chain.doFilter(new SecurityWrapper(req), res);
		} else {
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "Method not allowed");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
