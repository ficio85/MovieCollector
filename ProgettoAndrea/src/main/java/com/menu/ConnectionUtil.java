package com.menu;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
	private static ConnectionUtil instance;
	private static DataSource ds;
	
	private ConnectionUtil() throws Exception {
		InitialContext ic = null;
		try {
			System.out.println("Provaaaa");
			ic = new InitialContext();
			ds = (javax.sql.DataSource)ic.lookup("java:/ProvaDS");
			if (ds == null)
				throw new Exception("Illegal data source argument");
		} catch (NamingException ne) {
			logger.error("NamingException: "+ne.getMessage(), ne);
			throw new Exception("Could not look up context");
		}
	}
	
	public static ConnectionUtil getInstance() throws Exception  {
		synchronized (ConnectionUtil.class) {					
			if (instance == null)
				synchronized (ConnectionUtil.class) {								
					instance = new ConnectionUtil();
				}
		}
		return instance;
	}

	public synchronized DataSource getDataSource() {
		return ds;
	}
	
	public synchronized Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public void close(ResultSet rset, Statement stmt, Connection conn) throws SQLException {
		if(rset != null)
			rset.close();
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}

}
