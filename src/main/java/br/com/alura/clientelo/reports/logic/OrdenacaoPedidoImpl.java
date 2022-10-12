package br.com.alura.clientelo.reports.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	private static final Comparator<Pedido> CLIENTES_LUCRATIVOS_COMPARATOR = new LucrativoComparator();

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

	@Override
	public Map<String, TopClienteGastos> ordenarPorClienteLucrativo(List<Pedido> lista) {
		List<Pedido> pedidos = ordenar(lista, CLIENTES_LUCRATIVOS_COMPARATOR.thenComparing(NOME_CLIENTE_COMPARATOR));
		Map<String, TopClienteGastos> tops = new LinkedHashMap<>();
		for (Pedido pedido : pedidos) {
			BigDecimal totalPedido = pedido.getPreco().multiply(new BigDecimal(pedido.getQuantidade())).setScale(2, RoundingMode.HALF_DOWN);
			String nomeCliente = pedido.getCliente();
			if (tops.containsKey(nomeCliente)) {
				TopClienteGastos topClienteGastos = tops.get(nomeCliente);
				topClienteGastos.contabilizar(1, totalPedido);
				tops.put(nomeCliente, topClienteGastos);
			}
			tops.putIfAbsent(nomeCliente, new TopClienteGastos(1, totalPedido));
		}
		return limiteFiltro(tops, 2);
	}

	private <R,T> Map<R, T> limiteFiltro(Map<R, T> mapa, int limite) {
		Map<R, T> filtrados = new LinkedHashMap<>();

		int index = 0;
		for (Map.Entry<R, T> elemento : mapa.entrySet()) {
			if (++index > limite) break;
			filtrados.put(elemento.getKey(), elemento.getValue());
		}
		return filtrados;
	}

	private List<Pedido> ordenar(List<Pedido> lista, Comparator<Pedido> comparator) {
    	List<Pedido> pedido = new ArrayList<>(lista);
    	pedido.sort(comparator);
    	return pedido;
	}
}
