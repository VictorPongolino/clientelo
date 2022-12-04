package br.com.alura.clientelo.controller.pedido.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class DetalhePedidoDTO {
    @JsonProperty("data_pedido")
    private final LocalDate dataPedido;
    private final BigDecimal valor;
    private final BigDecimal desconto;
    @JsonProperty("detalhe_pedido")
    private final Set<DetalheProdutoPedidoDTO> detalheProduto;
    @JsonProperty("cliente_data")
    private final ClienteDetalhePedidoDTO clienteDetalhePedidoDTO;

    public DetalhePedidoDTO(LocalDate dataPedido, BigDecimal valor, BigDecimal desconto, Set<DetalheProdutoPedidoDTO> detalheProduto, ClienteDetalhePedidoDTO clienteDetalhePedidoDTO) {
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.desconto = desconto;
        this.detalheProduto = detalheProduto;
        this.clienteDetalhePedidoDTO = clienteDetalhePedidoDTO;
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

    public Set<DetalheProdutoPedidoDTO> getDetalheProduto() {
        return detalheProduto;
    }

    public static class ClienteDetalhePedidoDTO {
        private Long id;
        private String nome;

        public ClienteDetalhePedidoDTO(Long id, String nome) {
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

    public static class DetalheProdutoPedidoDTO {
        private final Long produtoId;
        private final String nome;
        private final String categoria;
        private final Integer quantidade;
        private final BigDecimal precoUnitario;
        private final BigDecimal valor;
        private final BigDecimal desconto;

        public DetalheProdutoPedidoDTO(Long produtoId, String nome, String categoria, Integer quantidade, BigDecimal precoUnitario, BigDecimal valor, BigDecimal desconto) {
            this.produtoId = produtoId;
            this.nome = nome;
            this.categoria = categoria;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.valor = valor;
            this.desconto = desconto;
        }

        public Long getProdutoId() {
            return produtoId;
        }

        public String getNome() {
            return nome;
        }

        public String getCategoria() {
            return categoria;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public BigDecimal getPrecoUnitario() {
            return precoUnitario;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public BigDecimal getDesconto() {
            return desconto;
        }
    }
}
