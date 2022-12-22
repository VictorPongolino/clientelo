package br.com.alura.clientelo.dao.relatorio;

import java.math.BigDecimal;

public class RelatorioVendasCategoriaVO {
    private String categoria;
    private Long quantidadeProdutosVendidos;
    private BigDecimal montanteVendido;

    public RelatorioVendasCategoriaVO(String categoria, Long quantidadeProdutosVendidos, BigDecimal montanteVendido) {
        this.categoria = categoria;
        this.quantidadeProdutosVendidos = quantidadeProdutosVendidos;
        this.montanteVendido = montanteVendido;
    }

    public String getCategoria() {
        return categoria;
    }

    public Long getQuantidadeProdutosVendidos() {
        return quantidadeProdutosVendidos;
    }

    public BigDecimal getMontanteVendido() {
        return montanteVendido;
    }

    @Override
    public String toString() {
        return "RelatorioVendasCategoriaDTO{" +
                "categoria='" + categoria + '\'' +
                ", quantidadeProdutosVendidos=" + quantidadeProdutosVendidos +
                ", montanteVendido=" + montanteVendido +
                '}';
    }
}
