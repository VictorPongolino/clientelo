package br.com.alura.clientelo.dao.relatorio;

import java.math.BigDecimal;

public class RelatorioClienteFielDTO {
    private String cliente;
    private Long quantidadePedidos;
    private BigDecimal montante;

    public RelatorioClienteFielDTO(String cliente, Long quantidadePedidos, BigDecimal montante) {
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

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(Long quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    public void setMontante(BigDecimal montante) {
        this.montante = montante;
    }

    @Override
    public String toString() {
        return "RelatorioClienteFielDTO{" +
                "cliente='" + cliente + '\'' +
                ", quantidadePedidos=" + quantidadePedidos +
                ", montante=" + montante +
                '}';
    }
}
