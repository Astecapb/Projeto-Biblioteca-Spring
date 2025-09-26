package com.projeto.pos.biblioteca.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projeto.pos.biblioteca.spring.dto.LivroDTO;

public interface LivroService {
    LivroDTO create(LivroDTO dto);
    LivroDTO update(Long id, LivroDTO dto);
    void delete(Long id);
    LivroDTO findById(Long id);
    Page<LivroDTO> findAll(String tituloFilter, Pageable pageable);
    long countExemplares(Long livroId);
    long countExemplaresByStatus(Long livroId, String status);
    // opcional: retornar um objeto com contagens por status
    Object countByAllStatuses(Long livroId);
}

