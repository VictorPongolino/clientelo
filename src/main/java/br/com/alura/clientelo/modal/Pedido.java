package br.com.alura.clientelo.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @OneToMany
    private List<ItemPedido> itempedidos;

    @ManyToOne(optional = false)
    private Cliente cliente;

    private BigDecimal preco;
    private int quantidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Pedido() {}

    public Pedido(Long id, Categoria categoria, List<ItemPedido> itempedidos, Cliente cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.id = id;
        this.categoria = categoria;
        this.itempedidos = itempedidos;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public boolean isMaisBaratoQue(Pedido pedido) {
        return this.getValorTotal().compareTo(pedido.getValorTotal()) < 0;
    }

    public boolean isMaisCaroQue(Pedido pedido) {
        return this.getValorTotal().compareTo(pedido.getValorTotal()) > 0;
    }

    public BigDecimal getValorTotal() {
        return this.preco.multiply(new BigDecimal(quantidade));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
