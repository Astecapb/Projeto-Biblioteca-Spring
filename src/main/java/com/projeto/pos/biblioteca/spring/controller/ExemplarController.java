package com.projeto.pos.biblioteca.spring.controller;


import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.pos.biblioteca.spring.dto.ExemplarDTO;
import com.projeto.pos.biblioteca.spring.service.ExemplarService;

import jakarta.validation.Valid;

/**
 * Controller para CRUD de Exemplares
 */

@RestController
@RequestMapping("/api/exemplares")
@CrossOrigin(origins = "*")
@Validated
public class ExemplarController {

    private final ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @PostMapping
    public ResponseEntity<ExemplarDTO> create(@Valid @RequestBody ExemplarDTO dto) {
        ExemplarDTO created = exemplarService.create(dto);
        return ResponseEntity.created(URI.create("/api/exemplares/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExemplarDTO> update(@PathVariable Long id,
                                              @Valid @RequestBody ExemplarDTO dto) {
        return ResponseEntity.ok(exemplarService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exemplarService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExemplarDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(exemplarService.findById(id));
    }

    /* path no plural para manter consistência */
    @GetMapping("/livros/{livroId}")
    public ResponseEntity<List<ExemplarDTO>> findByLivro(@PathVariable Long livroId) {
        return ResponseEntity.ok(exemplarService.findByLivroId(livroId));
    }

    @GetMapping
    public ResponseEntity<List<ExemplarDTO>> getAll() {
        return ResponseEntity.ok(exemplarService.findAll());
    }
}





