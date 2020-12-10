package by.htp.news.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.news.controller.command.NewsCommand;

public class ChangeLocale implements NewsCommand {

	private static final String PARAMETER_LANG = "lang";
	private static final String PARAMETER_CURRENT_PAGE = "currentPage";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String currentPage;
		
		HttpSession session = request.getSession();		
		session.setAttribute("locale", request.getParameter(PARAMETER_LANG));
		
		currentPage = (String)session.getAttribute(PARAMETER_CURRENT_PAGE);
        RequestDispatcher dispatcher = request.getRequestDispatcher(currentPage);
        dispatcher.forward(request, response);		
	}

}
