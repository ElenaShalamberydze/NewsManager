package by.htp.news.service;

import java.util.List;

import by.htp.news.entity.News;
import by.htp.news.service.validation.NewsValidatorException;

public interface NewsService {

	boolean create(News news) throws ServiceException, NewsValidatorException;
		
	News selectById(int id) throws ServiceException;
	
	List<News> selectAll() throws ServiceException;
		
	void update(News news) throws ServiceException, NewsValidatorException;
	
	void delete(int id) throws ServiceException;

}
