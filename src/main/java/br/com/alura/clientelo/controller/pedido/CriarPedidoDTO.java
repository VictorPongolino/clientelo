package br.com.alura.clientelo.controller.pedido;

import java.util.Collections;
import java.util.Set;

public class CriarPedidoDTO {
    private Long idCliente;
    private Set<CadastroPedidoResultado> cadastroPedidoResultado;

    public CriarPedidoDTO(Long idCliente, Set<CadastroPedidoResultado> cadastroPedidoResultado) {
        this.idCliente = idCliente;
        this.cadastroPedidoResultado = cadastroPedidoResultado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Set<CadastroPedidoResultado> getCadastroPedidoResultado() {
        return Collections.unmodifiableSet(cadastroPedidoResultado);
    }
}
