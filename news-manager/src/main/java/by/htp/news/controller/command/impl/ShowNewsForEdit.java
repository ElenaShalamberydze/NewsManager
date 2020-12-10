package by.htp.news.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.news.controller.command.NewsCommand;
import by.htp.news.entity.News;
import by.htp.news.service.NewsService;
import by.htp.news.service.ServiceException;
import by.htp.news.service.ServiceProvider;

public class ShowNewsForEdit implements NewsCommand {

	private static final String PARAMETER_ID = "id";
	private static final String PARAMETER_COMMAND = "command";
	
	private static final String EDIT_PAGE = "/WEB-INF/jsp/editNews.jsp";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		News news = null;
		int id;
		String page;
		String currentPage;
		
		ServiceProvider provider = ServiceProvider.getInstance();						
		NewsService service = provider.getNewsService();	
		
		try {
			id = Integer.parseInt(request.getParameter(PARAMETER_ID));
			news = service.selectById(id);
			news.setDate(LocalDate.now());								
			request.setAttribute("news", news);
			page = EDIT_PAGE;
																							   
			currentPage = "/controller?command=" + request.getParameter(PARAMETER_COMMAND) 
							+ "&id=" + request.getParameter(PARAMETER_ID);
			
			HttpSession session = request.getSession();
			session.setAttribute("currentPage", currentPage);							
			
		}catch(NumberFormatException | ServiceException e) {
			 request.setAttribute("errorId", "1");
	         page = ERROR_PAGE;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
				
	}

}
