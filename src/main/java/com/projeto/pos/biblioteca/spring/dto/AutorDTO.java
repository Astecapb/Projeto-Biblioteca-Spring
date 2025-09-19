package com.projeto.pos.biblioteca.spring.dto;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO simples para Autor.
 */
public class AutorDTO {
    private Long id;

    @NotBlank(message = "Nome do autor é obrigatório")
    private String nome;

    public AutorDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}