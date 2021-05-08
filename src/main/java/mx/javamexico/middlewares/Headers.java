package mx.javamexico.middlewares;

import io.jooby.Route.Before;

public class Headers {
	
	public static Before JsonHeaders = ctx -> {
		ctx.setResponseHeader("Content-type", "application/json; charset=utf-8");
	}; 

}
