package by.htp.news.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.news.controller.command.NewsCommand;

public class ShowCreatePage implements NewsCommand{
	
	private static final String CREATE_PAGE = "/WEB-INF/jsp/createNews.jsp";
	private static final String CURRENT_PAGE = "/controller?command=to_create";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String page = CREATE_PAGE;
		
        HttpSession session = request.getSession();
		session.setAttribute("currentPage", CURRENT_PAGE);	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
