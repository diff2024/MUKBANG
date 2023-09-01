package com.diff.map.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String URI = request.getRequestURI();
		
		if(URI.contains("/back/Common/")) {
			return true;
		}
		if(!URI.contains("/back/Common/")) {
			System.out.println("==================== BEGIN ======================");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String URI = request.getRequestURI();
		
		if(!URI.contains("/back/Common/")) {
			System.out.println("==================== END ======================");
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
