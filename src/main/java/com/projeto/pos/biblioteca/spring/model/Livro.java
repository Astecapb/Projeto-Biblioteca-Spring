package com.projeto.pos.biblioteca.spring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String isbn;
    private String editora;
    private Integer ano;

    // ManyToMany unidirecional para Autor (conforme dica do enunciado)
    @ManyToMany
    @JoinTable(
      name = "livro_autor",
      joinColumns = @JoinColumn(name = "livro_id"),
      inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();

    // OneToMany para exemplares; Livro não carrega os exemplares na criação de autores para evitar loops
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExemplarLivro> exemplares = new ArrayList<>();

    public Livro() {}

    // Getters / Setters
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

    public Set<Autor> getAutores() { return autores; }
    public void setAutores(Set<Autor> autores) { this.autores = autores; }

    public List<ExemplarLivro> getExemplares() { return exemplares; }
    public void setExemplares(List<ExemplarLivro> exemplares) { this.exemplares = exemplares; }
}
