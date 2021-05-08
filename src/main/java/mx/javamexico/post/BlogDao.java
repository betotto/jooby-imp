package mx.javamexico.post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;

import mx.javamexico.utils.DataSource;

public class BlogDao {

	private static Logger log = Logger.getLogger(BlogDao.class);
	private static String queryTodos = "SELECT post_id, DATE_FORMAT(fecha_alta, '%d/%m/%Y %T') AS fecha_alta, contenido FROM posts";
	
	public static CompletableFuture<List<Blog>> getAllPosts() {
		return CompletableFuture.supplyAsync(() -> {
			List<Blog> results = List.of();
			try (Connection conn = DataSource.getConnection();
					Statement stm  = conn.createStatement();
					ResultSet rs = stm.executeQuery(queryTodos);) {
				List<Blog> temp = new ArrayList<Blog>();
				while(rs.next()) {
					Blog p = new Blog();
					p.setContenido(rs.getString("contenido"));
					try {
						p.setFechaAlta(DataSource.singleDateFormater.parse(rs.getString("fecha_alta")));
					} catch(ParseException ex) {
						log.error("No se puede parsear la fecha, " + rs.getString("fecha_alta") + " del post " + rs.getInt("post_id"), ex);
					}
					p.setId(rs.getInt("post_id"));
					temp.add(p);
				}
				results =  Collections.unmodifiableList(temp);
			} catch(SQLException sqlx) {
				sqlx.printStackTrace();
				log.error("Hubo un problema con el query: " + queryTodos, sqlx);
			}
			return results;
		});
	}
}
