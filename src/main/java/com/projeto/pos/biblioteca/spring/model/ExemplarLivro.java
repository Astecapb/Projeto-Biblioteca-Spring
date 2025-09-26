package com.projeto.pos.biblioteca.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * Entidade ExemplarLivro representa cada cópia física/registro.
 */
@Getter
@Setter
@Entity
@Table(name = "exemplar_livro")
public class ExemplarLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // código único do exemplar (pode ser barcode)
    @Column(nullable = false, unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExemplarStatus status = ExemplarStatus.DISPONIVEL;

    // referência para o livro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Enumerated(EnumType.STRING)
@Column(name = "status_conservacao")
private StatusConservacao statusConservacao;

    public ExemplarLivro() { /* TODO document why this constructor is empty */ }

   /*  // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public ExemplarStatus getStatus() { return status; }
    public void setStatus(ExemplarStatus status) { this.status = status; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; } */
} 
