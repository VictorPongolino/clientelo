package br.com.alura.clientelo.controller.categoria.dto;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class CadastrarCategoriaDTO {

    @Size(min = 2)
    @ApiModelProperty(value = "Nome da categoria")
    private final String nome;

    public CadastrarCategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
