package br.com.alura.clientelo.controller.cliente;

import br.com.alura.clientelo.validator.Telefone;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public class CadastroClienteDTO {
    @NotBlank
    private final String nome;
    @NotBlank
    @CPF
    private final String cpf;
    @NotBlank
    @Telefone
    private final String telefone;
    @NotBlank
    private final String rua;
    @NotBlank
    private final String numero;
    private final String complemento;
    @NotBlank
    private final String bairro;
    @NotBlank
    private final String cidade;
    @NotBlank
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
