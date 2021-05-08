package mx.javamexico.categoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;

import mx.javamexico.utils.DataSource;

public class CategoriaDao {

	private static Logger log = Logger.getLogger(CategoriaDao.class);
	private static String queryTodas = "SELECT id_categoria, categoria FROM categorias";
	
	public static CompletableFuture<List<Categoria>> getAllCategorias() {
		return CompletableFuture.supplyAsync(() -> {
			List<Categoria> results = List.of();
			try (Connection conn = DataSource.getConnection();
					Statement stm  = conn.createStatement();
					ResultSet rs = stm.executeQuery(queryTodas);) {
				List<Categoria> temp = new ArrayList<Categoria>();
				while(rs.next()) {
					Categoria c = new Categoria();
					c.setCategoria(rs.getString("categoria"));
					c.setId(rs.getInt("id_categoria"));
					temp.add(c);
				}
				results =  Collections.unmodifiableList(temp);
				for(Categoria c : results) {
					log.info(c.getCategoria());
				}
			} catch(SQLException sqlx) {
				sqlx.printStackTrace();
				log.error("Hubo un problema con el query: " + queryTodas, sqlx);
			}
			return results;
		});
	}
	
}
