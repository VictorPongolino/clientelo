package br.com.alura.clientelo.controller.pedido.dto;

import java.util.Collections;
import java.util.Set;

public class CriarPedidoDTO {
    private final Long idCliente;
    private final Set<CadastroPedidoResultadoDTO> cadastroPedidoResultado;

    public CriarPedidoDTO(Long idCliente, Set<CadastroPedidoResultadoDTO> cadastroPedidoResultado) {
        this.idCliente = idCliente;
        this.cadastroPedidoResultado = cadastroPedidoResultado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Set<CadastroPedidoResultadoDTO> getCadastroPedidoResultado() {
        return Collections.unmodifiableSet(cadastroPedidoResultado);
    }
}
