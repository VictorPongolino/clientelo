package br.com.alura.clientelo.controller.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ListagemPedidosDTO {
    private final LocalDate dataPedido;
    private final BigDecimal valor;
    private final BigDecimal desconto;
    private final Long quantidadePedidos;
    private final ListagemPedidoCliente cliente;

    public ListagemPedidosDTO(LocalDate dataPedido, BigDecimal valor, BigDecimal desconto, Long quantidadePedidos, ListagemPedidoCliente cliente) {
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.desconto = desconto;
        this.quantidadePedidos = quantidadePedidos;
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public ListagemPedidoCliente getCliente() {
        return cliente;
    }

    static class ListagemPedidoCliente {
        private final Long id;
        private final String nome;

        public ListagemPedidoCliente(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
    }
}
