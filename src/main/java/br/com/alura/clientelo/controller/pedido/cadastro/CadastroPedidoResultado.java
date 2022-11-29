package br.com.alura.clientelo.controller.pedido.cadastro;

public class CadastroPedidoResultado {
    private final Long idProduto;
    private final Long quantidadeVendida;

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
