package com.sx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharactorFilter implements Filter{

	private String initParameter;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 initParameter = filterConfig.getInitParameter("charset");
	}
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(initParameter);
		chain.doFilter(request, response); 
	}

}
