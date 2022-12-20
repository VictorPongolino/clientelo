package br.com.alura.clientelo.controller.produto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CriarProdutoDTO {

    @NotNull
    @Size(min = 2)
    private String nomeProduto;
    @DecimalMin("0.1")
    private BigDecimal preco;
    private String descricao;
    @NotNull
    @Min(0)
    private Integer quantidadeEstoque;
    @NotNull
    @Min(1)
    private Integer categoriaId;

    @JsonCreator
    public CriarProdutoDTO(
            @JsonProperty("nome_produto") String nomeProduto, @JsonProperty("preco") BigDecimal preco,
            @JsonProperty("descricao") String descricao, @JsonProperty("quantidade_estoque") Integer quantidadeEstoque,
            @JsonProperty("categoria_id") Integer categoriaId) {
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
