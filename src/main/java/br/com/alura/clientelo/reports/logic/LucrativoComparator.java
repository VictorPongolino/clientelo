package br.com.alura.clientelo.reports.logic;

import br.com.alura.clientelo.Pedido;

import java.util.Comparator;

public class LucrativoComparator implements Comparator<Pedido> {
    @Override
    public int compare(Pedido o1, Pedido o2) {
        if (o1 == null) return 1;
        if (o2 == null) return -1;
        return o1.getPreco().compareTo(o2.getPreco());
    }
}
