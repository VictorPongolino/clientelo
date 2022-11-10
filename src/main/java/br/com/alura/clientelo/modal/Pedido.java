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

    private BigDecimal descontoTotal;
    private TipoDesconto tipoDesconto;

    public enum TipoDesconto {
        FIDELIDADE, NENHUM
    }

    Pedido() {}

    public Pedido(Categoria categoria, List<ItemPedido> itempedidos, Cliente cliente, LocalDate data) {
        this.categoria = categoria;
        this.itempedidos = itempedidos;
        this.cliente = cliente;
        this.data = data;
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

    public BigDecimal getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(BigDecimal descontoTotal) {
        this.descontoTotal = descontoTotal;
    }

    public TipoDesconto getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
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