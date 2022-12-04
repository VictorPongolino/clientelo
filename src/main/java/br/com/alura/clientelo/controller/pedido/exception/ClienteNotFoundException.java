package br.com.alura.clientelo.controller.pedido.exception;

public class ClienteNotFoundException extends RuntimeException {

    private Long clienteId;

    public ClienteNotFoundException(Long idCliente) {
        super("Cliente ID " + idCliente + " não encontrado!");
        this.clienteId = idCliente;
    }

    public Long getClienteId() {
        return clienteId;
    }
}
