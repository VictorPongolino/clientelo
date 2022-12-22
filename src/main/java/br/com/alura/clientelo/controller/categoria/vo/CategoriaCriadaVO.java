package br.com.alura.clientelo.controller.categoria.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaCriadaVO {
    @JsonProperty("categoria_id")
    private final Integer categoriaId;

    @JsonProperty("nome")
    private final String nome;

    public CategoriaCriadaVO(Integer categoriaId, String nome) {
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
