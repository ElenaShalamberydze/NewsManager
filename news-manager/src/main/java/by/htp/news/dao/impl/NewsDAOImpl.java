package by.htp.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.util.List;

import by.htp.news.dao.DAOException;
import by.htp.news.dao.NewsDAO;
import by.htp.news.dao.pool.ConnectionPool;
import by.htp.news.dao.pool.ConnectionPoolException;
import by.htp.news.entity.News;

public class NewsDAOImpl implements NewsDAO {
	
	private static ConnectionPool pool;
	private static boolean poolStatus;
	
	private static final String INSERT = "INSERT INTO news(title, brief, content, date) VALUES(?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM news ORDER BY date";
	private static final String SELECT_BY_ID = "SELECT * FROM news WHERE id=?";
	private static final String SELECT_IF_EXISTS = "SELECT title, brief, content "
													+ "FROM news WHERE title=? OR brief =? OR content =?";
	private static final String UPDATE = "UPDATE news SET title =?, brief=?, content=?, date=? WHERE id=?";
	private static final String DELETE = "DELETE FROM news WHERE id =?";
	
	static {
		try {
			pool = ConnectionPool.getInstance();
			poolStatus = true;
		}catch(ConnectionPoolException e) {
			poolStatus = false;
			System.out.println("Problems creating ConnectionPool!");
		}
	}
	

	@Override
	public boolean create(News news) throws DAOException {
		
		boolean result;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(poolStatus) {
			try {
				con = pool.takeConnection();
				ps = con.prepareStatement(SELECT_IF_EXISTS);
				
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getBrief());
				ps.setString(3, news.getContent());
				
				rs = ps.executeQuery();
				
				if(rs.next() == false) {
					ps = con.prepareStatement(INSERT);
					
					ps.setString(1, news.getTitle());
					ps.setString(2, news.getBrief());
					ps.setString(3, news.getContent());
					ps.setString(4, news.getDate().toString());
					
					ps.executeUpdate();
					result = true;
				}else {
					result = false;
				}
				
			}catch(SQLException e) {
				throw new DAOException();
			}catch(ConnectionPoolException e) {
				throw new DAOException();
			}finally {			
				try{
					pool.closeConnection(con, ps, rs);		
				}catch(SQLException e) {									
					throw new DAOException();
				}
			}
			
		}else {
			throw new DAOException();
		}
		
		return result;
	}
	
	
	@Override
	public News selectById(int id) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		News news = null;
		
		if(poolStatus) { 
			try {
				con = pool.takeConnection();				
				ps = con.prepareStatement(SELECT_BY_ID);
				
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				if(rs.next() != false) {
					news = new News();
					news.setId(Integer.parseInt(rs.getString("id")));
					news.setTitle(rs.getString("title"));
					news.setBrief(rs.getString("brief"));
					news.setContent(rs.getString("content"));
					news.setDate(rs.getDate("date").toLocalDate());											
				}
			
			}catch(ConnectionPoolException e) {
				throw new DAOException();
			}catch(SQLException e) {
				throw new DAOException();
			}finally {			
				try{
					pool.closeConnection(con, ps, rs);		
				}catch(SQLException e) {									
					throw new DAOException();
				}
			}
		}else {
			throw new DAOException();
		}
		
		return news;
	}
	

	@Override
	public List<News> selectAll() throws DAOException {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		News news = null;
		List<News> newsList = null;
		
		if(poolStatus) {
			try {
				con = pool.takeConnection();
				st = con.createStatement();
				rs = st.executeQuery(SELECT_ALL);
				newsList = new ArrayList<News>();
				
				while(rs.next()) {
					news = new News();					
					news.setId(Integer.parseInt(rs.getString("id")));
					news.setTitle(rs.getString("title"));
					news.setBrief(rs.getString("brief"));											
					news.setContent(rs.getString("content"));
					news.setDate(rs.getDate("date").toLocalDate());
					newsList.add(news);
				}
						
			}catch(SQLException e) {
				throw new DAOException();
			}catch(ConnectionPoolException e) {
				throw new DAOException();
			}finally {			
				try{
					pool.closeConnection(con, st, rs);		
				}catch(SQLException e) {									
					throw new DAOException();
				}
			}
		}else {
			throw new DAOException();
		}
		
		return newsList;
	}
	

	@Override
	public void update(News news) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		if(poolStatus) {
			try {
				con = pool.takeConnection();				
				ps = con.prepareStatement(UPDATE);
				
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getBrief());
				ps.setString(3, news.getContent());
				ps.setString(4, news.getDate().toString());
				ps.setInt(5, news.getId());
				
				ps.executeUpdate();
									
			}catch(SQLException e) {
				throw new DAOException();
			}catch(ConnectionPoolException e) {
				throw new DAOException();
			}finally {
				try {
					pool.closeConnection(con, ps);
				}catch(SQLException e) {
					throw new DAOException();
				}
			}
			
		}else {
			throw new DAOException();
		}		
	}
	

	@Override
	public void delete(int id) throws DAOException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		if(poolStatus) {
			try {
				con = pool.takeConnection();			
				ps = con.prepareStatement(DELETE);
				
				ps.setInt(1, id);
				ps.executeUpdate();
				
			}catch(SQLException e) {
				throw new DAOException();
			}catch(ConnectionPoolException e) {
				throw new DAOException();
			}finally {
				try {
					pool.closeConnection(con, ps);
				}catch(SQLException e) {
					throw new DAOException();
				}
			}
			
		}else {
			throw new DAOException();
		}
	}
	
}
