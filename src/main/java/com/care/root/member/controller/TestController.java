package com.care.root.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("test/index")
	public void test(HttpServletResponse res, HttpServletRequest req) throws IOException {
		System.out.println(req.getContextPath());
		String msg = "<script>alert('aaaaaaaa');";
		msg +="location.href='/root/member/login';</script>";
		res.setContentType("text/html; cjarset=utf-8");
		PrintWriter out = res.getWriter();
		out.print(msg);
	}
}
