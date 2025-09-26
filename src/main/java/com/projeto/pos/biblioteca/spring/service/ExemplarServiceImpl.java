package com.projeto.pos.biblioteca.spring.service;

import com.projeto.pos.biblioteca.spring.dto.ExemplarDTO;
import com.projeto.pos.biblioteca.spring.model.Livro;
import com.projeto.pos.biblioteca.spring.repository.ExemplarRepository;
import com.projeto.pos.biblioteca.spring.repository.LivroRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.projeto.pos.biblioteca.spring.model.ExemplarLivro;

@Service
@RequiredArgsConstructor
public class ExemplarServiceImpl implements ExemplarService {

    private final ExemplarRepository exemplarRepository;
    private final LivroRepository livroRepository;

    @Override
    @Transactional
    public ExemplarDTO create(ExemplarDTO dto) {
        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        ExemplarLivro exemplar = new ExemplarLivro();
        exemplar.setCodigo(dto.getCodigo());
        exemplar.setStatus(dto.getStatus());
        exemplar.setLivro(livro);

        return ExemplarDTO.fromEntity(exemplarRepository.save(exemplar));
    }

    @Override
    public ExemplarDTO update(Long id, ExemplarDTO dto) {
        ExemplarLivro exemplar = exemplarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exemplar não encontrado"));

        exemplar.setCodigo(dto.getCodigo());
        exemplar.setStatus(dto.getStatus());

        if (!exemplar.getLivro().getId().equals(dto.getLivroId())) {
            Livro livro = livroRepository.findById(dto.getLivroId())
                    .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
            exemplar.setLivro(livro);
        }

        return ExemplarDTO.fromEntity(exemplarRepository.save(exemplar));
    }

    @Override
    public void delete(Long id) {
        exemplarRepository.deleteById(id);
    }

    @Override
    public ExemplarDTO findById(Long id) {
        return exemplarRepository.findById(id)
                .map(ExemplarDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Exemplar não encontrado"));
    }

    @Override
    public List<ExemplarDTO> findByLivroId(Long livroId) {
        return exemplarRepository.findByLivroId(livroId)
                .stream()
                .map(ExemplarDTO::fromEntity)
                .toList();
    }

    @Override
    public List<ExemplarDTO> findAll() {
        return exemplarRepository.findAll()
                .stream()
                .map(ExemplarDTO::fromEntity)
                .toList();
    }
}
