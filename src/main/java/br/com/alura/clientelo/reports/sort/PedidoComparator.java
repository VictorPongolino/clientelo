package br.com.alura.clientelo.reports.sort;

import java.util.Comparator;

import br.com.alura.clientelo.Pedido;

public class PedidoComparator implements Comparator<Pedido> {
	@Override
	public int compare(Pedido o1, Pedido o2) {
		if (o2 == null) return -1;
		if (o1 == null) return 1;
		return o2.getQuantidade() - o1.getQuantidade();
	}
}
