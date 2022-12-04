package br.com.alura.clientelo.controller.pedido.dto.converter;

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
