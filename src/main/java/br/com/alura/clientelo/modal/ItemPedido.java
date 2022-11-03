package br.com.alura.clientelo.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    private Integer quantidade;
    @ManyToOne(optional = false)
    private Pedido pedido;
    @ManyToOne(optional = false)
    private Produto produto;
    private BigDecimal desconto;
    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;

    private ItemPedido() {}

    public ItemPedido(Long id, BigDecimal precoUnitario, Integer quantidade, Pedido pedido, Produto produto, BigDecimal desconto, TipoDesconto tipoDesconto) {
        this.id = id;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public enum TipoDesconto { QUANTIDADE, PROMOCAO, NENHUM }
}
