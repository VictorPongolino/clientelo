package br.com.alura.clientelo.controller.produto;

import java.math.BigDecimal;

public class ListagemProdutosDTO {
    private final String nome;
    private final BigDecimal preco;
    private final String descricao;
    private final Integer quantidadeEstoque;
    private final Long idCategoria;
    private final String nomeCategoria;

    public ListagemProdutosDTO(String nome, BigDecimal preco, String descricao, Integer quantidadeEstoque, Long idCategoria, String nomeCategoria) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
