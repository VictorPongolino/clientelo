package br.com.alura.clientelo.reports.logic.data;

import java.math.BigDecimal;

public class TopClienteGastos {
    private int quantidade;
    private BigDecimal totalGasto;

    public TopClienteGastos(int quantidade, BigDecimal totalGasto) {
        this.quantidade = quantidade;
        this.totalGasto = totalGasto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getTotalGasto() {
        return totalGasto;
    }

    public void contabilizar(int quantidade, BigDecimal gasto) {
        this.quantidade+= quantidade;
        this.totalGasto = totalGasto.add(gasto);
    }
}
