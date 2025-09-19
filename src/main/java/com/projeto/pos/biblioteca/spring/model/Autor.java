package com.projeto.pos.biblioteca.spring.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Construtores
    public Autor() {}
    public Autor(String nome) { this.nome = nome; }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // equals/hashCode baseados em id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(getId(), autor.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
