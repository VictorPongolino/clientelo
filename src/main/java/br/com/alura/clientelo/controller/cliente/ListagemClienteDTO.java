package br.com.alura.clientelo.controller.cliente;

public class ListagemClienteDTO {
    private final String nome;
    private final String cpf;
    private final String telefone;
    private final String local;

    public ListagemClienteDTO(String nome, String cpf, String telefone, String local) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.local = local;
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

    public String getLocal() {
        return local;
    }
}
