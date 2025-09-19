package com.projeto.pos.biblioteca.spring.service;


import com.projeto.pos.biblioteca.spring.dto.ExemplarDTO;
import java.util.List;

public interface ExemplarService {
    ExemplarDTO create(ExemplarDTO dto);
    ExemplarDTO update(Long id, ExemplarDTO dto);
    void delete(Long id);
    ExemplarDTO findById(Long id);
    List<ExemplarDTO> findByLivroId(Long livroId);
}
