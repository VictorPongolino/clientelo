package br.com.alura.clientelo.modal.pedido;

import br.com.alura.clientelo.modal.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    private Integer quantidade;
    @ManyToOne(optional = false)
    @JsonBackReference
    private Pedido pedido;
    @ManyToOne(optional = false)
    private Produto produto;
    private BigDecimal desconto;
    @Column(name = "tipo_desconto")
    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;

    ItemPedido() {}

    public ItemPedido(BigDecimal precoUnitario, Integer quantidade, Pedido pedido, Produto produto, BigDecimal desconto, TipoDesconto tipoDesconto) {
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

    public void adicionarItem(int quantidade) {
        this.quantidade += quantidade;
        this.precoUnitario = this.precoUnitario.add(this.precoUnitario.multiply(new BigDecimal(quantidade)));
    }

    public void applicarDesconto(TipoDesconto tipoDesconto, BigDecimal valor) {
        this.desconto = valor;
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getPrecoFinal() {
        return getMontanteTotal().subtract(desconto);
    }

    public BigDecimal getMontanteTotal() {
        return this.precoUnitario.multiply(new BigDecimal(quantidade));
    }

    public boolean hasQuantidadeItem() {
        return this.quantidade == 0;
    }

    public enum TipoDesconto { QUANTIDADE, PROMOCAO, NENHUM }
}
