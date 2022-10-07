package br.com.alura.clientelo.reports.sort;

import br.com.alura.clientelo.Pedido;

import java.util.Comparator;

public class QuantidadeComparator implements Comparator<Pedido> {
    @Override
    public int compare(Pedido o1, Pedido o2) {
        if (o1 == null) return 1;
        if (o2 == null) return -1;
        return o1.getQuantidade() - o2.getQuantidade();
    }
}
