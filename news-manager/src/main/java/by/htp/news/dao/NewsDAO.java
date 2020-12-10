package by.htp.news.dao;

import java.util.List;

import by.htp.news.entity.News;

public interface NewsDAO {
		
	boolean create(News news) throws DAOException;									
	
	News selectById(int id) throws DAOException;
		
	List<News> selectAll() throws DAOException;
	
	void update(News news) throws DAOException;										
	
	void delete(int id) throws DAOException;
	
}
