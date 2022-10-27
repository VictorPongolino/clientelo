package br.com.alura.clientelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private final String categoria;
    private final String produto;
    private final String cliente;

    private final BigDecimal preco;
    private final int quantidade;

    private final LocalDate data;

    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
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
        // TODO: Investigar do Scaling em casos de multiplicação.
        return this.preco.multiply(new BigDecimal(quantidade));
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
