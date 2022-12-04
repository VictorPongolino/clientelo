package br.com.alura.clientelo.controller.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AtualizarProdutoDTO {

    @NotEmpty
    @Length(min = 2)
    private final String nome;
    @DecimalMin(value = "0.0")
    private final BigDecimal preco;
    private final String descricao;
    @Min(0)
    private final Integer quantidade;
    @NotNull
    @JsonProperty("categoria_id")
    private final Integer categoriaId;

    public AtualizarProdutoDTO(String nome, BigDecimal preco, String descricao, Integer quantidade, Integer categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoriaId = categoriaId;
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

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }
}
