package br.com.alura.clientelo.controller.pedido;

public class CadastroPedidoResultado {
    private Long idProduto;
    private Long quantidadeVendida;

    public CadastroPedidoResultado(Long idProduto, Long quantidadeVendida) {
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
