package by.htp.news.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.news.controller.command.NewsCommand;
import by.htp.news.controller.command.NewsCommandProvider;

public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String PARAMETER_COMMAND = "command";
	
	private final NewsCommandProvider provider = new NewsCommandProvider();

    public NewsController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName = request.getParameter(PARAMETER_COMMAND);
		NewsCommand command = provider.getCommand(commandName);

		command.execute(request, response);

	}

}

