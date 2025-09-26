package com.projeto.pos.biblioteca.spring.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para criação/edição de Livro.
 * Envia apenas os campos que existem no banco (titulo, isbn, editora, ano).
 * autoresIds = lista de IDs de autores já cadastrados.
 */

@Data
@Getter
@Setter
public class LivroDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200)
    private String titulo;

    @Size(max = 20)
    private String isbn;

    @Size(max = 100)
    private String editora;

    private Integer ano;

    private List<Long> autoresIds = new ArrayList<>();

    /* ---------- getters / setters ---------- */

   /*  public Long getId() { return id; }
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
    public void setAutoresIds(List<Long> autoresIds) { this.autoresIds = autoresIds; } */

    /* ---------- utilitário ---------- */
    @Override
    public String toString() {
        return "LivroDTO{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", isbn='" + isbn + '\'' +
               ", editora='" + editora + '\'' +
               ", ano=" + ano +
               ", autoresIds=" + autoresIds +
               '}';
    }
}