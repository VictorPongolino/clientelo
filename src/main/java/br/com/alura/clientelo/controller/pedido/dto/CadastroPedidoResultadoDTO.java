package br.com.alura.clientelo.controller.pedido.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastroPedidoResultadoDTO {
    @JsonProperty("produto_id")
    private final Long idProduto;
    @JsonProperty("quantidade_vendida")
    private final Long quantidadeVendida;

    public CadastroPedidoResultadoDTO(Long idProduto, Long quantidadeVendida) {
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
