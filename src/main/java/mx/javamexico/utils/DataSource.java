package mx.javamexico.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	
	private static Logger log = Logger.getLogger(DataSource.class);
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	public static void init(String environment, String DB_USER, String DB_PASSWORD) {
		log.info("Iniciando Datasource " + environment);
		if(!environment.equals("test")) {
			String databaseScript = DataSource.class.getClassLoader().getResource("populate.sql").getPath();
			System.out.println(databaseScript);
			config.setJdbcUrl("jdbc:h2:mem:javamexico;INIT=RUNSCRIPT FROM '" + databaseScript + "'");
			config.setUsername(DB_USER);
			config.setAutoCommit(false);
			config.setMaximumPoolSize(1);
			config.setDriverClassName("org.h2.Driver");
			ds = new HikariDataSource(config);
		} else {
			config.setJdbcUrl("jdbc:mysql://localhost:3306/javamexico?allowPublicKeyRetrieval=true&useSSL=false");
			config.setUsername(DB_USER);
			config.setPassword(DB_PASSWORD);
			config.setAutoCommit(false);
			config.setMaximumPoolSize(5);
			config.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds = new HikariDataSource(config);
		}
	}
	
	public final static DateFormat singleDateFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
