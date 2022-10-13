package br.com.alura.clientelo.reports.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.sort.NomeClienteComparator;
import br.com.alura.clientelo.reports.sort.QuantidadeComparator;

public class OrdenacaoPedidoImpl implements OrdenacaoPedido<Pedido> {

	private static final Comparator<Pedido> NOME_CLIENTE_COMPARATOR = new NomeClienteComparator();
	private static final Comparator<Pedido> QUANTIDADE_COMPARATOR = new QuantidadeComparator().reversed();

	private static final Comparator<Pedido> CLIENTES_LUCRATIVOS_COMPARATOR = new LucrativoComparator();

	@Override
	public List<Pedido> ordenarQuantidade(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(Comparator.comparing(Pedido::getQuantidade).reversed())
				.limit(3)
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, QuantidadeCategoria> ordenarPorCategoria(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(Comparator.comparing(Pedido::getCategoria))
				.collect(Collectors.toMap(Pedido::getCategoria, valor -> new QuantidadeCategoria(valor.getQuantidade(), valor.getPreco().multiply(new BigDecimal(valor.getQuantidade()))), (k,v) -> {
					k.atualizar(v.getQuantidade(), v.getMontante());
					return k;
				}));
	}
	
	@Override
	public Map<String, Pedido> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(Comparator.comparing(Pedido::getCategoria))
				.collect(Collectors.toMap(Pedido::getCategoria, pedido -> pedido, (k, v) -> {
					BigDecimal preco1 = k.getPreco().multiply(new BigDecimal(k.getQuantidade()));
					BigDecimal preco2 = v.getPreco().multiply(new BigDecimal(v.getQuantidade()));
					return preco1.compareTo(preco2) > 0 ? k : v;
				}, LinkedHashMap::new));
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
