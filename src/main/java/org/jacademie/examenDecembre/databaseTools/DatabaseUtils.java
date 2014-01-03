package org.jacademie.examenDecembre.databaseTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DatabaseUtils {

	private static Logger logger = Logger.getLogger(DatabaseUtils.class);
	
	private static final String DRIVER_CLASS_NAME = "org.hsqldb.jdbc.JDBCDriver";
	private static final String JDBC_USER = "SA";
	private static final String JDBC_PASSWORD = "";
	private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/mydb";
	
	
	static{
		try {
			Class.forName(DRIVER_CLASS_NAME);
			logger.info("DB Driver loaded");
		} catch (ClassNotFoundException e) {
			logger.fatal(e);
		}
	}
	
	public static Connection getConnection(){
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
			logger.debug("Connection done");
			return connection;
		} catch (SQLException e) {
			logger.fatal(e);
		}

		return null;
		
	}
}
