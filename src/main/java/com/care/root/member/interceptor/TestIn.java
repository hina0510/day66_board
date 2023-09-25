package com.care.root.member.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.member.common.LoginSession;

public class TestIn extends HandlerInterceptorAdapter implements LoginSession{
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("새로운 인터셉터 등록!!!");
		HttpSession session = req.getSession();
		if(StringUtils.isEmpty(session.getAttribute(LOGIN))) {
			String msg = "<script>alert('aaaaaaaa');";
			msg +="location.href='/root/member/login';</script>";
			res.setContentType("text/html; cjarset=utf-8");
			PrintWriter out = res.getWriter();
			out.print(msg);
			return false;
		}
		return true;
	}
}
