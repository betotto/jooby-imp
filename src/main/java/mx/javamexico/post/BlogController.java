package mx.javamexico.post;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BlogController {
	public static CompletableFuture<List<Blog>> getPosts() {
		return BlogDao.getAllPosts();
	}
}
