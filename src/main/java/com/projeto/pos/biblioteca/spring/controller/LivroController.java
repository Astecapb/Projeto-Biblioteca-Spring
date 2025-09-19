package com.projeto.pos.biblioteca.spring.controller;



import com.projeto.pos.biblioteca.spring.dto.LivroDTO;
import com.projeto.pos.biblioteca.spring.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "*") // liberar CORS para testes locais; ajuste em produção
@Validated
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Criar livro
    @PostMapping
    public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto) {
        LivroDTO criado = livroService.create(dto);
        return ResponseEntity.status(201).body(criado);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        LivroDTO atualizado = livroService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // Remover
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.findById(id));
    }

    // Listar / buscar por título via query string ?titulo=abc
    @GetMapping
    public ResponseEntity<Page<LivroDTO>> list(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("titulo").ascending());
        Page<LivroDTO> resultado = livroService.findAll(titulo, pageable);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint para contar exemplares:
    // GET /api/livros/{id}/exemplares/count  -> retorna mapa com contagens por status
    // GET /api/livros/{id}/exemplares/count?status=DISPONIVEL -> retorna apenas número
    @GetMapping("/{id}/exemplares/count")
    public ResponseEntity<?> countExemplares(
            @PathVariable Long id,
            @RequestParam(value = "status", required = false) String status
    ) {
        if (status == null) {
            Object map = livroService.countByAllStatuses(id);
            return ResponseEntity.ok(map);
        } else {
            long count = livroService.countExemplaresByStatus(id, status);
            return ResponseEntity.ok(Map.of("status", status, "count", count));
        }
    }
}

