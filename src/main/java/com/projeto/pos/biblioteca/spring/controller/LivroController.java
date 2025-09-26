package com.projeto.pos.biblioteca.spring.controller;



import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.pos.biblioteca.spring.dto.LivroDTO;
import com.projeto.pos.biblioteca.spring.service.LivroService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*")
@Validated
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto) {
        return ResponseEntity.status(201).body(livroService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id,
    @Valid @RequestBody LivroDTO dto) {
        return ResponseEntity.ok(livroService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> list(
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("titulo").ascending());
        return ResponseEntity.ok(livroService.findAll(titulo, pageable));
    }

    @GetMapping("/{id}/exemplares/count")
    public ResponseEntity<?> countExemplares(
            @PathVariable Long id,
            @RequestParam(required = false) String status
    ) {
        if (status == null) {
            return ResponseEntity.ok(livroService.countByAllStatuses(id));
        } else {
            long count = livroService.countExemplaresByStatus(id, status);
            return ResponseEntity.ok(Map.of("status", status, "count", count));
        }
    }
}


