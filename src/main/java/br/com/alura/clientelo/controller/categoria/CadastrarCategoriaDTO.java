package br.com.alura.clientelo.controller.categoria;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

public class CadastrarCategoriaDTO {

    @Size(min = 2)
    private final String nome;

    public CadastrarCategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
