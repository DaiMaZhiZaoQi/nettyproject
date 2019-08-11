package com.sx.myException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class UserExceptionResolver implements HandlerExceptionResolver {
	@ExceptionHandler
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mav=new ModelAndView();
		if(ex instanceof MyException){
			mav.setViewName("error-my");
			mav.addObject("ex", ex.getMessage());
		}else{
			mav.addObject("ex", ex.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}

}
