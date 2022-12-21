package br.com.alura.clientelo.controller.produto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProdutoCriadoDTO {

    @JsonProperty("produto_id")
    private final Long produtoId;
    @JsonProperty("nome_produto")
    private final String nomeProduto;
    private final BigDecimal preco;
    private final String descricao;
    @JsonProperty("quantidade_estoque")
    private final Integer quantidadeEstoque;
    @JsonProperty("categoria_id")
    private final Integer categoriaId;

    public ProdutoCriadoDTO(Long produtoId, String nomeProduto, BigDecimal preco, String descricao, Integer quantidadeEstoque, Integer categoriaId) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoriaId = categoriaId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
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

    public Integer getCategoriaId() {
        return categoriaId;
    }

}
