package br.com.alura.clientelo.leitor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoCSVDTO {

    @CsvBindByName(column = "CATEGORIA")
    private final String categoria;
    @CsvBindByName(column = "PRODUTO")
    private final String produto;
    @CsvBindByName(column = "CLIENTE")
    private final String cliente;

    @CsvBindByName(column = "PRECO")
    private final BigDecimal preco;
    @CsvBindByName(column = "QUANTIDADE")
    private final int quantidade;

    @CsvDate(value = "dd/MM/yyyy")
    @CsvBindByName(column = "DATA")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    public PedidoCSVDTO(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
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
}
