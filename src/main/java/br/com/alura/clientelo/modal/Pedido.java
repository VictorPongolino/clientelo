package br.com.alura.clientelo.modal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itempedidos;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    Pedido() {}

    public Pedido(Categoria categoria, List<ItemPedido> itempedidos, Cliente cliente, LocalDate data) {
        this.categoria = categoria;
        this.itempedidos = itempedidos;
        this.cliente = cliente;
        this.data = data;
    }

//    public boolean isMaisBaratoQue(Pedido pedido) {
//        return this.getValorTotal().compareTo(pedido.getValorTotal()) < 0;
//    }

//    public boolean isMaisCaroQue(Pedido pedido) {
//        return this.getValorTotal().compareTo(pedido.getValorTotal()) > 0;
//    }

//    public BigDecimal getValorTotal() {
//        return this.preco.multiply(new BigDecimal(quantidade));
//    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItempedidos() {
        return itempedidos;
    }

    public void setItempedidos(List<ItemPedido> itempedidos) {
        this.itempedidos = itempedidos;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
                ", data=" + data +
                '}';
    }

}