package by.htp.news.dao;

import by.htp.news.dao.impl.NewsDAOImpl;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final NewsDAOImpl newsDAO = new NewsDAOImpl();
	
	private DAOProvider() {}
	
	public NewsDAOImpl getNewsDAO() {
		return newsDAO;
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

}
