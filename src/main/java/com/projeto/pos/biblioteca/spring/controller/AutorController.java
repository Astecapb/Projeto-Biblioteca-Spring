package com.projeto.pos.biblioteca.spring.controller;


import com.projeto.pos.biblioteca.spring.dto.AutorDTO;
import com.projeto.pos.biblioteca.spring.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Controller para CRUD de Autores
 */
@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "*")
@Validated
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorDTO> create(@Valid @RequestBody AutorDTO dto) {
        AutorDTO created = autorService.create(dto);
        return ResponseEntity.created(URI.create("/api/autores/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> update(@PathVariable Long id, @Valid @RequestBody AutorDTO dto) {
        AutorDTO updated = autorService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listAll() {
        return ResponseEntity.ok(autorService.findAll());
    }
}
