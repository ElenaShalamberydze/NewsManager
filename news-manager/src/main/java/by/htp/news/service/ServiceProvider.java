package by.htp.news.service;

import by.htp.news.service.impl.NewsServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();	
	
	private ServiceProvider() {}
	
	private final NewsServiceImpl newsService = new NewsServiceImpl();
	
	
	public NewsServiceImpl getNewsService() {
		return newsService;		
	}
	
	public static ServiceProvider getInstance() {
		return instance;		
	}
}

