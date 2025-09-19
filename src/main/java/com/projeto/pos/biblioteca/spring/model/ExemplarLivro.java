package com.projeto.pos.biblioteca.spring.model;

import jakarta.persistence.*;


/**
 * Entidade ExemplarLivro representa cada cópia física/registro.
 */

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

    public ExemplarLivro() {}

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public ExemplarStatus getStatus() { return status; }
    public void setStatus(ExemplarStatus status) { this.status = status; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }
}
