package br.com.alura.clientelo.reports.logic;

import br.com.alura.clientelo.Pedido;

import java.math.BigDecimal;
import java.util.Comparator;

public class LucrativoComparator implements Comparator<Pedido> {
    @Override
    public int compare(Pedido o1, Pedido o2) {
        if (o1 == null) return 1;
        if (o2 == null) return -1;
        return o2.getPreco().multiply(new BigDecimal(o2.getQuantidade())).compareTo(o1.getPreco().multiply(new BigDecimal(o1.getQuantidade())));
    }
}
