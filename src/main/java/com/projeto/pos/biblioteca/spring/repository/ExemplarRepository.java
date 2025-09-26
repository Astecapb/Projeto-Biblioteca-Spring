package com.projeto.pos.biblioteca.spring.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.pos.biblioteca.spring.model.ExemplarLivro;
import com.projeto.pos.biblioteca.spring.model.ExemplarStatus;


public interface ExemplarRepository extends JpaRepository<ExemplarLivro, Long> {
    long countByLivroId(Long livroId);
    long countByLivroIdAndStatus(Long livroId, ExemplarStatus status);

    public Object findByLivroId(Long livroId);
}
