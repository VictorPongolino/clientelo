package br.com.alura.clientelo.reports.logic;

import java.math.BigDecimal;

public class QuantidadeCategoria {

    private int quantidade;
    private BigDecimal montante;

    public QuantidadeCategoria(int quantidade, BigDecimal montante) {
        this.quantidade = quantidade;
        this.montante = montante;
    }

    public void atualizar(int quantidade, BigDecimal montante) {
        this.quantidade += quantidade;
        this.montante = this.montante.add(montante);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getMontante() {
        return montante;
    }
}
