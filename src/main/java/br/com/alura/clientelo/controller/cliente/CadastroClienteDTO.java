package br.com.alura.clientelo.controller.cliente;

import javax.validation.constraints.NotEmpty;

public class CadastroClienteDTO {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String cpf;
    @NotEmpty
    private final String telefone;
    @NotEmpty
    private final String rua;
    @NotEmpty
    private final String numero;
    private final String complemento;
    @NotEmpty
    private final String bairro;
    @NotEmpty
    private final String cidade;
    @NotEmpty
    private final String estado;

    public CadastroClienteDTO(String nome, String cpf, String telefone, String rua, String numero, String complemento, String bairro, String cidade, String estado) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
