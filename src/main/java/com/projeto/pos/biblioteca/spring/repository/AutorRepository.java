package com.projeto.pos.biblioteca.spring.repository;



import com.projeto.pos.biblioteca.spring.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
