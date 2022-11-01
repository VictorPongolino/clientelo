package br.com.alura.clientelo.reports.relatorios;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.Map;

import br.com.alura.clientelo.reports.RelatorioMostruario;
import br.com.alura.clientelo.reports.logic.data.QuantidadeCategoria;
import br.com.alura.clientelo.reports.utils.DinheiroUtils;

public class RelatorioVendasCategoria extends OperacaoPedido<Pedido> implements RelatorioMostruario {
	
	public RelatorioVendasCategoria(List<Pedido> pedidos) {
		super(pedidos);
	}

	private Map<String, QuantidadeCategoria> ordenarPorCategoria() {
		 return getPedidos().stream()
				.sorted(comparing(Pedido::getCategoria))
				.collect(toMap(Pedido::getCategoria, valor -> new QuantidadeCategoria(valor.getQuantidade(), valor.getPreco().multiply(new BigDecimal(valor.getQuantidade()))), (k, v) -> {
					k.atualizar(v.getQuantidade(), v.getMontante());
					return k;
				}));
	}

	@Override
	public void accept() {
//		printTitle("Relatório de vendas por categoria");
		ordenarPorCategoria().forEach((categoria, pedido) -> {
			System.out.printf("Categoria: %s\nQuantidade: %s\nMontante: %s\n\n", categoria, pedido.getQuantidade(), DinheiroUtils.formatarDinheiroBrasileiro(pedido.getMontante()));
		});
	}

}
