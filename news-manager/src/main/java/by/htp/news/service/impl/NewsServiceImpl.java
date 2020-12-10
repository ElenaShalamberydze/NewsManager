package by.htp.news.service.impl;

import java.util.List;

import by.htp.news.dao.DAOException;
import by.htp.news.dao.DAOProvider;
import by.htp.news.dao.NewsDAO;
import by.htp.news.entity.News;
import by.htp.news.service.NewsService;
import by.htp.news.service.ServiceException;
import by.htp.news.service.validation.NewsValidator;
import by.htp.news.service.validation.NewsValidatorException;

public class NewsServiceImpl implements NewsService{

	@Override
	public boolean create(News news) throws ServiceException, NewsValidatorException{
		
		if(!NewsValidator.isCorrect(news)) {			
			throw new NewsValidatorException();
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO newsDAO = daoProvider.getNewsDAO();
		
		try {
			return newsDAO.create(news);
		}catch(DAOException e) {
			throw new ServiceException();
		}
	}
	
	
	@Override
	public News selectById(int id) throws ServiceException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();								
		NewsDAO newsDAO = daoProvider.getNewsDAO();											
		News news = null;																	
		
		try {
			news = newsDAO.selectById(id);
		}catch(DAOException e){
			throw new ServiceException();
		}
		
		return news;
	}
	

	@Override
	public List<News> selectAll() throws ServiceException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO newsDAO = daoProvider.getNewsDAO();
		List<News> newsList = null;
		
		try {
			newsList = newsDAO.selectAll();
		}catch(DAOException e) {
			throw new ServiceException();
		}
		return newsList;
	}
		

	@Override
	public void update(News news) throws ServiceException, NewsValidatorException {
		
		if(!NewsValidator.isCorrect(news)) {
			throw new NewsValidatorException();
		}
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO newsDAO = daoProvider.getNewsDAO();
		
		try {
			newsDAO.update(news);
		}catch(DAOException e) {
			throw new ServiceException();
		}
	}
	

	@Override
	public void delete(int id) throws ServiceException {
		
		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO newsDAO = daoProvider.getNewsDAO();
		
		try {
			newsDAO.delete(id);
		}catch(DAOException e) {
			throw new ServiceException();
		}
	}

}

