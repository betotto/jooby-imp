package mx.javamexico.post;

import java.util.Map;

import io.jooby.Jooby;
import io.jooby.ModelAndView;
import mx.javamexico.categoria.CategoriaController;
import mx.javamexico.middlewares.Headers;
import mx.javamexico.utils.GsonParser;

public class BlogRoutes extends Jooby {
	{
		path("/blogs", () -> {
			get("/index.html", ctx -> {
				Map<String, Object> model = Map.of("perro", "hola");

				return new ModelAndView("index.hbs", model);
			});

			routes(() -> {
				before(Headers.JsonHeaders);
				path("/services", () -> {
					get("/get-all", ctx -> BlogController.getPosts()
						.thenApply(list -> GsonParser.parser.toJson(list))
						.exceptionally(ex -> "[]"));
				});
				
				path("/services", () -> {
					get("/get-categorias", ctx -> CategoriaController.getCategorias()
						.thenApply(list -> GsonParser.parser.toJson(list))
						.exceptionally(ex -> "[]"));
				});
			});
		});
	}
}
