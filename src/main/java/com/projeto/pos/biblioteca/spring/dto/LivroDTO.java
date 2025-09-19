package com.projeto.pos.biblioteca.spring.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para criação/edição de Livro. autoresIds é lista de IDs de autores.
 */
public class LivroDTO {
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String isbn;
    private String editora;
    private Integer ano;

    private List<Long> autoresIds;

    public LivroDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public List<Long> getAutoresIds() { return autoresIds; }
    public void setAutoresIds(List<Long> autoresIds) { this.autoresIds = autoresIds; }
}