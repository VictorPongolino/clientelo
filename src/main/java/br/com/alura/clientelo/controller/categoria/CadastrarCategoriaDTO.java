package br.com.alura.clientelo.controller.categoria;

import org.hibernate.validator.constraints.Length;

public class CadastrarCategoriaDTO {

    @Length(min = 2)
    private final String nome;

    public CadastrarCategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
