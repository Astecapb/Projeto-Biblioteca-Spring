package com.projeto.pos.biblioteca.spring.service;

import com.projeto.pos.biblioteca.spring.dto.AutorDTO;
import java.util.List;

public interface AutorService {
    AutorDTO create(AutorDTO dto);
    AutorDTO update(Long id, AutorDTO dto);
    void delete(Long id);
    AutorDTO findById(Long id);
    List<AutorDTO> findAll();
}