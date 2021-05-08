CREATE SCHEMA IF NOT EXISTS javamexico;

CREATE TABLE IF NOT EXISTS categorias (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_categoria)
);

CREATE TABLE IF NOT EXISTS posts (
    id_post INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(150),
    fecha_alta DATETIME NOT NULL,
    contenido BLOB,
    tipo INT NOT NULL,
    id_categoria INT,
    status INT,
    PRIMARY KEY (id_post),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    fecha_alta DATETIME NOT NULL,
    nombre_usuario VARCHAR(100) NOT NULL,
    contrasenia VARCHAR(600),
    PRIMARY KEY (id_usuario)
);

CREATE TABLE IF NOT EXISTS posts_por_usuario (
	id_post INT NOT NULL,
	id_usuario INT NOT NULL,
	visitas INT NOT NULL DEFAULT 0,
	UNIQUE (id_post, id_usuario),
	FOREIGN KEY (id_post) REFERENCES posts(id_post),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS comentarios_por_post (
	id_comentario INT NOT NULL,
	id_post INT NOT NULL,
	id_usuario INT NOT NULL,
	UNIQUE (id_post, id_usuario),
	FOREIGN KEY (id_comentario) REFERENCES posts(id_post),
	FOREIGN KEY (id_post) REFERENCES posts(id_post),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS foros (
	id_post INT NOT NULL,
	id_usuario INT NOT NULL,
	fecha_alta DATETIME NOT NULL,
	UNIQUE (id_post, id_usuario),
	FOREIGN KEY (id_post) REFERENCES posts(id_post),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);


INSERT INTO categorias (categoria) VALUES
('DESARROLLO'),
('TEST'),
('PRODUCCION');

COMMIT;
