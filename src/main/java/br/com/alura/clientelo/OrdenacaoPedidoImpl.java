package br.com.alura.clientelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdenacaoPedidoImpl implements OrdenacaoPedido<Pedido> {

	@Override
	public List<Pedido> ordenarQuantidade(List<Pedido> pedidos) {
		return ordenar(pedidos, new PedidoComparator()).subList(0, 3);
	}

	@Override
	public List<Pedido> ordenarPorCategoria(List<Pedido> pedidos) {
		return ordenar(pedidos, new CategoriaPedidoComparator());
	}
	
	private List<Pedido> ordenar(List<Pedido> lista, Comparator<Pedido> comparator) {
    	List<Pedido> pedido = new ArrayList<>(lista);
    	pedido.sort(comparator);
    	return pedido;
	}

}
