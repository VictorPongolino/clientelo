package br.com.alura.clientelo.modal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itempedidos;

    @ManyToOne(optional = false)
    private Cliente cliente;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @Column(name = "desconto_total")
    private BigDecimal descontoTotal;
    @Column(name = "tipo_desconto")
    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;

    public enum TipoDesconto {
        FIDELIDADE, NENHUM
    }

    Pedido() {}

    public Pedido(List<ItemPedido> itempedidos, Cliente cliente, LocalDate data) {
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

    public BigDecimal getPrecoFinal() {
        return itempedidos.stream().map(ItemPedido::getPrecoFinal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                ", cliente='" + cliente + '\'' +
                ", data=" + data +
                '}';
    }

}