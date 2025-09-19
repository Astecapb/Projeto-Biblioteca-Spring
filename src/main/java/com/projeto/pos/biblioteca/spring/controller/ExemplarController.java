package com.projeto.pos.biblioteca.spring.controller;


import com.projeto.pos.biblioteca.spring.dto.ExemplarDTO;
import com.projeto.pos.biblioteca.spring.service.ExemplarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Controller para CRUD de Exemplares
 */
@RestController
@RequestMapping("/api/exemplares")
@CrossOrigin(origins = "*")
@Validated
public class ExemplarController {

    @Autowired
    private ExemplarService exemplarService;

    @PostMapping
    public ResponseEntity<ExemplarDTO> create(@Valid @RequestBody ExemplarDTO dto) {
        ExemplarDTO created = exemplarService.create(dto);
        return ResponseEntity.created(URI.create("/api/exemplares/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExemplarDTO> update(@PathVariable Long id, @Valid @RequestBody ExemplarDTO dto) {
        ExemplarDTO updated = exemplarService.update(id, dto);
        return ResponseEntity.ok(updated);
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

    @GetMapping("/livro/{livroId}")
    public ResponseEntity<List<ExemplarDTO>> findByLivro(@PathVariable Long livroId) {
        return ResponseEntity.ok(exemplarService.findByLivroId(livroId));
    }
}
