package br.com.alura.clientelo.controller.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoCriadoDTO {
    private final Long id;
    @JsonProperty("item_pedido")
    private final List<ItemPedidoCriadoDTO> itemPedido;
    @JsonProperty("cliente_id")
    private final Long clienteId;
    private final LocalDate data;
    @JsonProperty("desconto_total")
    private final BigDecimal descontoTotal;
    @JsonProperty("tipo_desconto")
    private final String tipoDesconto;

    public PedidoCriadoDTO(Long id, List<ItemPedidoCriadoDTO> itempedidos, Long clienteId, LocalDate data, BigDecimal descontoTotal, String tipoDesconto) {
        this.id = id;
        this.itemPedido = itempedidos;
        this.clienteId = clienteId;
        this.data = data;
        this.descontoTotal = descontoTotal;
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
    }

    public List<ItemPedidoCriadoDTO> getItemPedido() {
        return itemPedido;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getDescontoTotal() {
        return descontoTotal;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public static class ItemPedidoCriadoDTO {
        @JsonProperty("preco_unitario")
        private final BigDecimal precoUnitario;
        private final Integer quantidade;
        @JsonProperty("produto_id")
        private final Long produtoId;
        private final BigDecimal desconto;
        @JsonProperty("tipo_desconto")
        private final String tipoDesconto;

        public ItemPedidoCriadoDTO(BigDecimal precoUnitario, Integer quantidade, Long produtoId, BigDecimal desconto, String tipoDesconto) {
            this.precoUnitario = precoUnitario;
            this.quantidade = quantidade;
            this.produtoId = produtoId;
            this.desconto = desconto;
            this.tipoDesconto = tipoDesconto;
        }

        public BigDecimal getPrecoUnitario() {
            return precoUnitario;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public Long getProdutoId() {
            return produtoId;
        }

        public BigDecimal getDesconto() {
            return desconto;
        }

        public String getTipoDesconto() {
            return tipoDesconto;
        }
    }
}
