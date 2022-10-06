package br.com.alura.clientelo.reports.sort;

import java.util.Comparator;

import br.com.alura.clientelo.Pedido;

public class CategoriaPedidoComparator implements Comparator<Pedido> {
	@Override
	public int compare(Pedido o1, Pedido o2) {
		if (o1 == null || o1.getCategoria() == null) return 1;
		if (o2 == null || o2.getCategoria() == null) return -1;
		return o1.getCategoria().compareTo(o2.getCategoria());
	}
}
