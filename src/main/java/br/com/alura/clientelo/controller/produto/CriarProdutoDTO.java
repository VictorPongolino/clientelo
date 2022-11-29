package br.com.alura.clientelo.controller.produto;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CriarProdutoDTO {

    @NotNull
    @Length(min = 2)
    private final String nomeProduto;
    @DecimalMin("0.1")
    private final BigDecimal preco;
    private final String descricao;
    @NotNull
    private final Integer quantidadeEstoque;
    @NotNull
    private final Long categoriaId;

    public CriarProdutoDTO(String nomeProduto, BigDecimal preco, String descricao, Integer quantidadeEstoque, Long categoriaId) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoriaId = categoriaId;
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

    public Long getCategoriaId() {
        return categoriaId;
    }
}
