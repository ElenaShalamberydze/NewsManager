package by.htp.news.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.news.controller.command.NewsCommand;
import by.htp.news.entity.News;
import by.htp.news.service.NewsService;
import by.htp.news.service.ServiceException;
import by.htp.news.service.ServiceProvider;
import by.htp.news.service.validation.NewsValidatorException;

public class UpdateNews implements NewsCommand {
	
	private static final String PARAMETER_ID = "id";
	private static final String PARAMETER_TITLE = "title";
	private static final String PARAMETER_DATE = "date";
	private static final String PARAMETER_BRIEF = "brief";
	private static final String PARAMETER_CONTENT = "content";
	
	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	private static final String PARAMETER_PATH = "controller?command=show_news&id=";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int id = Integer.parseInt(request.getParameter(PARAMETER_ID));
		String title = request.getParameter(PARAMETER_TITLE);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(request.getParameter(PARAMETER_DATE), formatter);
		
		String brief = request.getParameter(PARAMETER_BRIEF);
		String content = request.getParameter(PARAMETER_CONTENT);
		String page;
		
		ServiceProvider provider = ServiceProvider.getInstance();						
		NewsService service = provider.getNewsService();
		
		News news = new News();
		news.setId(id);
		news.setTitle(title);
		news.setDate(date);
		news.setBrief(brief);
		news.setContent(content);
		
		try {
			service.update(news);
			
			page = PARAMETER_PATH + request.getParameter(PARAMETER_ID);
			
		}catch(ServiceException e) {
			request.setAttribute("errorId", "1");
			page = ERROR_PAGE;
		}catch(NewsValidatorException e) {
			request.setAttribute("errorId", "4");
			page = ERROR_PAGE;
		}
		
		if(page.equals(ERROR_PAGE)){
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}else {
			response.sendRedirect("controller?command=show_all");
		}
	}

}
