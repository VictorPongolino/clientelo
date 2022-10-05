package br.com.alura.clientelo;

import java.util.Comparator;

public class CategoriaPedidoComparator implements Comparator<Pedido> {
	@Override
	public int compare(Pedido o1, Pedido o2) {
		if (o1 == null || o1.getCategoria() == null) return 1;
		if (o2 == null || o2.getCategoria() == null) return -1;
		return o1.getCategoria().compareTo(o2.getCategoria());
	}
}
