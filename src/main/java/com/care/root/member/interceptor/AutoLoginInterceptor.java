package com.care.root.member.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.care.root.member.common.LoginSession;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter implements LoginSession{
	@Autowired MemberService ms;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object Handle) throws Exception{
		Cookie loginCookie = WebUtils.getCookie(req, "loginCookie");
		if(loginCookie != null) {
			MemberDTO dto = ms.getUserSessionId(loginCookie.getValue());
			if(dto != null) {
				req.getSession().setAttribute(LOGIN, dto.getId());
			}
		}
		return true;
	}
}
