package mx.javamexico.categoria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import mx.javamexico.utils.DataSource;

@TestInstance(Lifecycle.PER_CLASS)
public class CategoriaDaoTest {
	
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeAll 
	public void setUp() {
		DataSource.init("test", "sa", "");
	}
	
	@BeforeEach
	public void setUpSingle() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@AfterEach
	public void tearDown() {
	    System.setOut(standardOut);
	}

	@Test
	void deberia_obtner_todas_las_categorias() throws Exception {
		CompletableFuture<List<Categoria>> result = CategoriaDao.getAllCategorias();
		
		List<Categoria> categorias = result.get();
		
		
		System.out.println(categorias);
		assertThat(categorias)
        	.hasSize(3);
		
		
	}
}
