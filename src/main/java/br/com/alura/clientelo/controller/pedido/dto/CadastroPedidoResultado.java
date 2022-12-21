package br.com.alura.clientelo.controller.pedido.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastroPedidoResultado {

    private final Long idProduto;
    private final Long quantidadeVendida;

    @JsonCreator
    public CadastroPedidoResultado(@JsonProperty("produto_id") Long idProduto, @JsonProperty("quantidade_vendida") Long quantidadeVendida) {
        this.idProduto = idProduto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
