package br.com.alura.clientelo.controller.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CriarProdutoDTO {

    @NotNull
    @Length(min = 2)
    @JsonProperty("nome_produto")
    private final String nomeProduto;
    @DecimalMin("0.1")
    private final BigDecimal preco;
    private final String descricao;
    @NotNull
    @Min(0)
    @JsonProperty("quantidade_estoque")
    private final Integer quantidadeEstoque;
    @NotNull
    @JsonProperty("categoria_id")
    private final Integer categoriaId;

    public CriarProdutoDTO(String nomeProduto, BigDecimal preco, String descricao, Integer quantidadeEstoque, Integer categoriaId) {
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

    public Integer getCategoriaId() {
        return categoriaId;
    }
}
