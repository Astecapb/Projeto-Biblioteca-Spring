package com.projeto.pos.biblioteca.spring.service;


import com.projeto.pos.biblioteca.spring.dto.LivroDTO;
import com.projeto.pos.biblioteca.spring.exception.ResourceNotFoundException;
import com.projeto.pos.biblioteca.spring.model.Autor;
import com.projeto.pos.biblioteca.spring.model.ExemplarStatus;
import com.projeto.pos.biblioteca.spring.model.Livro;
import com.projeto.pos.biblioteca.spring.repository.AutorRepository;
import com.projeto.pos.biblioteca.spring.repository.ExemplarRepository;
import com.projeto.pos.biblioteca.spring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Livro. Faz mapeamentos simples entre DTO e entidade.
 */
@Service
@Transactional
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    private LivroDTO toDTO(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setIsbn(livro.getIsbn());
        dto.setEditora(livro.getEditora());
        dto.setAno(livro.getAno());
        dto.setAutoresIds(livro.getAutores().stream().map(Autor::getId).collect(Collectors.toList()));
        return dto;
    }

    private void applyDtoToEntity(LivroDTO dto, Livro entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setEditora(dto.getEditora());
        entity.setAno(dto.getAno());

        // se houver lista de autores, buscamos e atribuímos
        if (dto.getAutoresIds() != null) {
            List<Autor> autores = autorRepository.findAllById(dto.getAutoresIds());
            entity.setAutores(new HashSet<>(autores));
        }
    }

    @Override
    public LivroDTO create(LivroDTO dto) {
        Livro livro = new Livro();
        applyDtoToEntity(dto, livro);
        Livro saved = livroRepository.save(livro);
        return toDTO(saved);
    }

    @Override
    public LivroDTO update(Long id, LivroDTO dto) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro", id));
        applyDtoToEntity(dto, livro);
        Livro saved = livroRepository.save(livro);
        return toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro", id);
        }
        livroRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Livro", id));
        return toDTO(livro);
    }

    @Override
    public Page<LivroDTO> findAll(String tituloFilter, Pageable pageable) {
        Page<Livro> page;
        if (tituloFilter == null || tituloFilter.isBlank()) {
            page = livroRepository.findAll(pageable);
        } else {
            page = livroRepository.findByTituloContainingIgnoreCase(tituloFilter, pageable);
        }
        return page.map(this::toDTO);
    }

    @Override
    public long countExemplares(Long livroId) {
        return exemplarRepository.countByLivroId(livroId);
    }

    @Override
    public long countExemplaresByStatus(Long livroId, String status) {
        ExemplarStatus s = ExemplarStatus.valueOf(status.toUpperCase());
        return exemplarRepository.countByLivroIdAndStatus(livroId, s);
    }

    @Override
    public Object countByAllStatuses(Long livroId) {
        long total = exemplarRepository.countByLivroId(livroId);
        long disponiveis = exemplarRepository.countByLivroIdAndStatus(livroId, ExemplarStatus.DISPONIVEL);
        long emprestados = exemplarRepository.countByLivroIdAndStatus(livroId, ExemplarStatus.EMPRESTADO);
        long reservados = exemplarRepository.countByLivroIdAndStatus(livroId, ExemplarStatus.RESERVADO);
        Map<String, Long> map = new LinkedHashMap<>();
        map.put("total", total);
        map.put("disponivel", disponiveis);
        map.put("emprestado", emprestados);
        map.put("reservado", reservados);
        return map;
    }
}

