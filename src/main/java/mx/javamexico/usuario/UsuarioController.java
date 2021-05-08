package mx.javamexico.usuario;

import io.jooby.Jooby;

public class UsuarioController extends Jooby {
	{
		get("/foo", ctx -> "Welcome to Jooby!");
	}
}