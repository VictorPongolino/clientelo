package br.com.alura.clientelo.controller.pedido.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

public class CriarPedidoDTO {
    @NotNull
    @Min(1)
    @JsonProperty("cliente_id")
    private final Long idCliente;
    @JsonProperty("pedidos")
    private final Set<CadastroPedidoResultado> cadastroPedidoResultado;

    @JsonCreator
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
