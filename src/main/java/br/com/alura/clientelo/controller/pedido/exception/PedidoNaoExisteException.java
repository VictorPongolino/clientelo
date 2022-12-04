package br.com.alura.clientelo.controller.pedido.exception;

public class PedidoNaoExisteException extends RuntimeException {
    private Long pedidoId;
    public PedidoNaoExisteException(Long pedidoId) {
        super("Pedido ID " + pedidoId + " não existe!");
        this.pedidoId = pedidoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }
}
