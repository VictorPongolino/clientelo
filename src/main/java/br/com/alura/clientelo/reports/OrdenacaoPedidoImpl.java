package br.com.alura.clientelo.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.sort.CategoriaPedidoComparator;
import br.com.alura.clientelo.reports.sort.PedidoComparator;

public class OrdenacaoPedidoImpl implements OrdenacaoPedido<Pedido> {

	@Override
	public List<Pedido> ordenarQuantidade(List<Pedido> pedidos) {
		return ordenar(pedidos, new PedidoComparator()).subList(0, 3);
	}

	@Override
	public List<Pedido> ordenarPorCategoria(List<Pedido> pedidos) {
		return ordenar(pedidos, new CategoriaPedidoComparator());
	}
	
	@Override
	public Map<String, Pedido> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos) {
		List<Pedido> ordenarPorCategoria = ordenarPorCategoria(pedidos);
		Map<String, Pedido> pedidosFiltrados = new LinkedHashMap<String, Pedido>();
		for (Pedido pedido : ordenarPorCategoria) {
			String categoria = pedido.getCategoria();
			if (pedidosFiltrados.containsKey(categoria)) {
				BigDecimal preco = pedidosFiltrados.get(categoria).getPreco();
				if (preco.compareTo(pedido.getPreco()) < 0) {
					pedidosFiltrados.put(categoria, pedido);
				}
			}
			
			pedidosFiltrados.putIfAbsent(categoria, pedido);
		}
		
		return pedidosFiltrados;
	}
	
	private List<Pedido> ordenar(List<Pedido> lista, Comparator<Pedido> comparator) {
    	List<Pedido> pedido = new ArrayList<>(lista);
    	pedido.sort(comparator);
    	return pedido;
	}

}
