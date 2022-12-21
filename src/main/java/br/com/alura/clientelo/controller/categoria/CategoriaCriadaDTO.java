package br.com.alura.clientelo.controller.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaCriadaDTO {
    @JsonProperty("categoria_id")
    private final Integer categoriaId;

    @JsonProperty("nome")
    private final String nome;

    public CategoriaCriadaDTO(Integer categoriaId, String nome) {
        this.categoriaId = categoriaId;
        this.nome = nome;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public String getNome() {
        return nome;
    }
}
