-- Criação da tabela autor
CREATE TABLE autor (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL
);

-- Criação da tabela livro
CREATE TABLE livro (
  id SERIAL PRIMARY KEY,
  titulo VARCHAR(500) NOT NULL,
  isbn VARCHAR(50),
  editora VARCHAR(255),
  ano INTEGER
);

-- Tabela de associação livro_autor (ManyToMany)
CREATE TABLE livro_autor (
  livro_id INTEGER NOT NULL REFERENCES livro(id) ON DELETE CASCADE,
  autor_id INTEGER NOT NULL REFERENCES autor(id) ON DELETE CASCADE,
  PRIMARY KEY (livro_id, autor_id)
);

-- Enum status
CREATE TYPE exemplar_status AS ENUM ('DISPONIVEL', 'EMPRESTADO', 'RESERVADO');

-- Tabela exemplar_livro
CREATE TABLE exemplar_livro (
  id SERIAL PRIMARY KEY,
  codigo VARCHAR(100) UNIQUE NOT NULL,
  status exemplar_status NOT NULL DEFAULT 'DISPONIVEL',
  livro_id INTEGER NOT NULL REFERENCES livro(id) ON DELETE CASCADE
);
