package com.projeto.pos.biblioteca.spring.repository;

import com.projeto.pos.biblioteca.spring.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // busca por título utilizando query string (containing + ignore case)
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
