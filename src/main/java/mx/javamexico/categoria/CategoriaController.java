package mx.javamexico.categoria;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CategoriaController {
	public static CompletableFuture<List<Categoria>> getCategorias() {
		return CategoriaDao.getAllCategorias();
	}
}
