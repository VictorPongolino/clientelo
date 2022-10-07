package br.com.alura.clientelo.reports.logic;

import java.math.BigDecimal;
import java.util.*;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.sort.CategoriaPedidoComparator;
import br.com.alura.clientelo.reports.sort.NomeClienteComparator;
import br.com.alura.clientelo.reports.sort.PedidoComparator;
import br.com.alura.clientelo.reports.sort.QuantidadeComparator;

public class OrdenacaoPedidoImpl implements OrdenacaoPedido<Pedido> {

	private static final Comparator<Pedido> NOME_CLIENTE_COMPARATOR = new NomeClienteComparator();
	private static final Comparator<Pedido> QUANTIDADE_COMPARATOR = new QuantidadeComparator().reversed();
	private static final Comparator<Pedido> CATEGORIA_COMPARATOR = new CategoriaPedidoComparator();
	private static final Comparator<Pedido> PEDIDO_COMPARATOR = new PedidoComparator();

	@Override
	public List<Pedido> ordenarQuantidade(List<Pedido> pedidos) {
		return ordenar(pedidos, PEDIDO_COMPARATOR).subList(0, 3);
	}

	@Override
	public List<Pedido> ordenarPorCategoria(List<Pedido> pedidos) {
		return ordenar(pedidos, CATEGORIA_COMPARATOR);
	}
	
	@Override
	public Map<String, Pedido> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos) {
		List<Pedido> ordenarPorCategoria = ordenarPorCategoria(pedidos);
		Map<String, Pedido> pedidosFiltrados = new LinkedHashMap<>();
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

	@Override
	public Map<String, Integer> ordenarPorClientes(List<Pedido> lista) {
		List<Pedido> ordenar = ordenar(lista, QUANTIDADE_COMPARATOR.thenComparing(NOME_CLIENTE_COMPARATOR));
		Map<String, Integer> quantidadeFiltrada = new HashMap<>();
		for (Pedido pedido : ordenar) {
			Integer quantidadePedidosCliente = pedido.getQuantidade();
			String nomeCliente = pedido.getCliente();
			if (quantidadeFiltrada.containsKey(nomeCliente)) {
				quantidadeFiltrada.put(nomeCliente, quantidadeFiltrada.get(nomeCliente) + quantidadePedidosCliente);
			}
			quantidadeFiltrada.putIfAbsent(nomeCliente, quantidadePedidosCliente);
		}
		return quantidadeFiltrada;
	}

	private List<Pedido> ordenar(List<Pedido> lista, Comparator<Pedido> comparator) {
    	List<Pedido> pedido = new ArrayList<>(lista);
    	pedido.sort(comparator);
    	return pedido;
	}
}
