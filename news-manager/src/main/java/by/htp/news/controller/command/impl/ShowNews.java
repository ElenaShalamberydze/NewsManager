package by.htp.news.controller.command.impl;

import java.io.IOException;

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

public class ShowNews implements NewsCommand {
	
	private static final String ID = "id";
	
	private static final String SHOW_NEWS_PAGE = "/WEB-INF/jsp/showNews.jsp";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int id;
		News news = null;		
		String page;
		String currentPage;
		
		ServiceProvider provider = ServiceProvider.getInstance();						
		NewsService service = provider.getNewsService();	
		
		try {
			id = Integer.parseInt(request.getParameter(ID));
			news = service.selectById(id);
			request.setAttribute("news", news);
			page = SHOW_NEWS_PAGE;
			
			currentPage = "/controller?" + request.getQueryString();
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
