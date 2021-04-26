package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		if(request.getMethod().equals("GET")) {
			System.out.println("get 요청이 왔다.");
		}
		else if(request.getMethod().equals("POST")) {
			System.out.println("post 요청이 왔다.");
		}
		
		super.service(request, response);		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) {
		System.out.println("doPost 메서드가 호출되었다.");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse rsp) {
		System.out.println("doGet 메서드가 호출되었다.");
	}
}
