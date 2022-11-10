package br.com.alura.clientelo.dao.relatorio;

import java.math.BigDecimal;

public class RelatorioClienteFielDTO {
    private String cliente;
    private Integer quantidadePedidos;
    private BigDecimal montante;

    public RelatorioClienteFielDTO(String cliente, Integer quantidadePedidos, BigDecimal montante) {
        this.cliente = cliente;
        this.quantidadePedidos = quantidadePedidos;
        this.montante = montante;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(Integer quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    public void setMontante(BigDecimal montante) {
        this.montante = montante;
    }
}
