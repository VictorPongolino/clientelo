package br.com.alura.clientelo.reports.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import br.com.alura.clientelo.Pedido;
import br.com.alura.clientelo.reports.sort.NomeClienteComparator;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class OrdenacaoPedidoImpl implements OrdenacaoPedido<Pedido> {

	private static final Comparator<Pedido> NOME_CLIENTE_COMPARATOR = new NomeClienteComparator();

	private static final Comparator<Pedido> CLIENTES_LUCRATIVOS_COMPARATOR = new LucrativoComparator();

	@Override
	public List<Pedido> ordenarQuantidade(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(comparing(Pedido::getQuantidade).reversed())
				.limit(3)
				.collect(toList());
	}

	@Override
	public Map<String, QuantidadeCategoria> ordenarPorCategoria(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(comparing(Pedido::getCategoria))
				.collect(toMap(Pedido::getCategoria, valor -> new QuantidadeCategoria(valor.getQuantidade(), valor.getPreco().multiply(new BigDecimal(valor.getQuantidade()))), (k, v) -> {
					k.atualizar(v.getQuantidade(), v.getMontante());
					return k;
				}));
	}
	
	@Override
	public Map<String, Pedido> ordenarPorCategoriaMaiorPreco(List<Pedido> pedidos) {
		return pedidos.stream()
				.sorted(comparing(Pedido::getCategoria))
				.collect(toMap(Pedido::getCategoria, pedido -> pedido, (k, v) -> {
					BigDecimal preco1 = k.getPreco().multiply(new BigDecimal(k.getQuantidade()));
					BigDecimal preco2 = v.getPreco().multiply(new BigDecimal(v.getQuantidade()));
					return preco1.compareTo(preco2) > 0 ? k : v;
				}, LinkedHashMap::new));
	}

	@Override
	public Map<String, Integer> ordenarPorClientes(List<Pedido> lista) {
		LinkedHashMap<String, Integer> ordemPorClientes = lista.stream()
					.sorted(comparing(Pedido::getCliente))
					.collect(groupingBy(Pedido::getCliente, LinkedHashMap::new, summingInt(x -> 1)));
		// TODO: Ver se consegue chamar de alguma forma o groupingBy
		return ordemPorClientes.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x, LinkedHashMap::new));
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
