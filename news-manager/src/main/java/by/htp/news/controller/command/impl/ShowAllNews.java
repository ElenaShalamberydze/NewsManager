package by.htp.news.controller.command.impl;

import java.io.IOException;
import java.util.List;

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

public class ShowAllNews implements NewsCommand {
	
	private static final String CURRENT_PAGE = "/controller?command=show_all";
	
	private static final String SHOW_ALL_PAGE = "/WEB-INF/jsp/showAllNews.jsp";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		List<News> newsList = null;
		String page;
		
		ServiceProvider provider = ServiceProvider.getInstance();						
		NewsService service = provider.getNewsService();	
		
		try {
            newsList = service.selectAll();            
            request.setAttribute("newsList", newsList);
            page = SHOW_ALL_PAGE;

	        HttpSession session = request.getSession();
			session.setAttribute("currentPage", CURRENT_PAGE);	
			
        } catch (ServiceException e) {
            request.setAttribute("errorId", "1");
            page = ERROR_PAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
		
	}

}
