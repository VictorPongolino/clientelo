package br.com.alura.clientelo.controller.pedido.dto;

public class CadastroPedidoResultadoDTO {
    private final Long idProduto;
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
