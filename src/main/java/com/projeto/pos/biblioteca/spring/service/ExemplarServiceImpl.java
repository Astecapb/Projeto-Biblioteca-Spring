package com.projeto.pos.biblioteca.spring.service;


import com.projeto.pos.biblioteca.spring.dto.ExemplarDTO;
import com.projeto.pos.biblioteca.spring.exception.ResourceNotFoundException;
import com.projeto.pos.biblioteca.spring.model.ExemplarLivro;
import com.projeto.pos.biblioteca.spring.model.Livro;
import com.projeto.pos.biblioteca.spring.repository.ExemplarRepository;
import com.projeto.pos.biblioteca.spring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para exemplares
 */
@Service
@Transactional
public class ExemplarServiceImpl implements ExemplarService {

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private LivroRepository livroRepository;

    private ExemplarDTO toDTO(ExemplarLivro e) {
        ExemplarDTO dto = new ExemplarDTO();
        dto.setId(e.getId());
        dto.setCodigo(e.getCodigo());
        dto.setStatus(e.getStatus());
        dto.setLivroId(e.getLivro().getId());
        return dto;
    }

    @Override
    public ExemplarDTO create(ExemplarDTO dto) {
        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new ResourceNotFoundException("Livro", dto.getLivroId()));
        ExemplarLivro e = new ExemplarLivro();
        e.setCodigo(dto.getCodigo());
        e.setStatus(dto.getStatus());
        e.setLivro(livro);
        ExemplarLivro saved = exemplarRepository.save(e);
        return toDTO(saved);
    }

    @Override
    public ExemplarDTO update(Long id, ExemplarDTO dto) {
        ExemplarLivro e = exemplarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemplar", id));
        if (!e.getCodigo().equals(dto.getCodigo())) {
            e.setCodigo(dto.getCodigo());
        }
        e.setStatus(dto.getStatus());
        if (!e.getLivro().getId().equals(dto.getLivroId())) {
            Livro l = livroRepository.findById(dto.getLivroId())
                    .orElseThrow(() -> new ResourceNotFoundException("Livro", dto.getLivroId()));
            e.setLivro(l);
        }
        return toDTO(exemplarRepository.save(e));
    }

    @Override
    public void delete(Long id) {
        if (!exemplarRepository.existsById(id)) throw new ResourceNotFoundException("Exemplar", id);
        exemplarRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ExemplarDTO findById(Long id) {
        ExemplarLivro e = exemplarRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exemplar", id));
        return toDTO(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExemplarDTO> findByLivroId(Long livroId) {
        return exemplarRepository.findAll().stream()
                .filter(e -> e.getLivro() != null && e.getLivro().getId().equals(livroId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

