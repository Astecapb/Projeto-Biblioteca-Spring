package com.projeto.pos.biblioteca.spring.dto;

import com.projeto.pos.biblioteca.spring.model.ExemplarLivro;
import com.projeto.pos.biblioteca.spring.model.ExemplarStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para Exemplar.
 */


public class ExemplarDTO {

    private Long id;

    @NotBlank(message = "Código do exemplar é obrigatório")
    private String codigo;

    @NotNull(message = "Status é obrigatório")
    private ExemplarStatus status;

    @NotNull(message = "livroId é obrigatório")
    private Long livroId;

    public ExemplarDTO() {}

    /* getters e setters */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public ExemplarStatus getStatus() { return status; }
    public void setStatus(ExemplarStatus status) { this.status = status; }

    public Long getLivroId() { return livroId; }
    public void setLivroId(Long livroId) { this.livroId = livroId; }

    /* ---------- MÉTODO CORRIGIDO ---------- */
    public static ExemplarDTO fromEntity(ExemplarLivro entity) {
        ExemplarDTO dto = new ExemplarDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setStatus(entity.getStatus());
        dto.setLivroId(entity.getLivro().getId());
        return dto;
    }
}