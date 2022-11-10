package br.com.alura.clientelo.dao.relatorio;

import java.math.BigDecimal;

public class RelatorioVendasCategoriaDTO {
    private String categoria;
    private Integer quantidadeProdutosVendidos;
    private BigDecimal montanteVendido;

    public RelatorioVendasCategoriaDTO(String categoria, Integer quantidadeProdutosVendidos, BigDecimal montanteVendido) {
        this.categoria = categoria;
        this.quantidadeProdutosVendidos = quantidadeProdutosVendidos;
        this.montanteVendido = montanteVendido;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQuantidadeProdutosVendidos() {
        return quantidadeProdutosVendidos;
    }

    public BigDecimal getMontanteVendido() {
        return montanteVendido;
    }
}
