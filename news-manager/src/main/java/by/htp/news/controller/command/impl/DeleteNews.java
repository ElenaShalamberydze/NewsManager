package by.htp.news.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.news.controller.command.NewsCommand;
import by.htp.news.service.NewsService;
import by.htp.news.service.ServiceException;
import by.htp.news.service.ServiceProvider;

public class DeleteNews implements NewsCommand {
	private static final String PARAMETER_DELETE_ID = "deleteId";
	
	private static final String START_PAGE = "index.jsp";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int id;
		String page;
		
		ServiceProvider provider = ServiceProvider.getInstance();						
		NewsService service = provider.getNewsService();	
		String[] deleteNews = request.getParameterValues(PARAMETER_DELETE_ID);
		
		try {
			for(String delete : deleteNews) {
				id = Integer.parseInt(delete);
				service.delete(id);
			}            
			page = START_PAGE;
		}catch(NullPointerException e) {
			request.setAttribute("errorId", "2");
			page = ERROR_PAGE;
		}catch(NumberFormatException | ServiceException e) {
			request.setAttribute("errorId", "1");
            page = ERROR_PAGE;
		}

		if(page.equals(START_PAGE)) {
			response.sendRedirect("controller?command=show_all");
		}else {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}

}
