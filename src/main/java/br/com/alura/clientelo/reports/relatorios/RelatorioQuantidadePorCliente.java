package br.com.alura.clientelo.reports.relatorios;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;

import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.reports.RelatorioMostruario;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RelatorioQuantidadePorCliente extends OperacaoPedido<Pedido> implements RelatorioMostruario {
    public RelatorioQuantidadePorCliente(List<Pedido> pedidos) {
        super(pedidos);
    }

    public LinkedHashMap<String, Integer> getOrdemPorClientes() {
        LinkedHashMap<String, Integer> ordemPorClientes = getPedidos().stream()
                .sorted(comparing(Pedido::getCliente))
                .collect(groupingBy(Pedido::getCliente, LinkedHashMap::new, summingInt(x -> 1)));

        return ordemPorClientes.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> x, LinkedHashMap::new));
    }

    @Override
    public void accept() {
//        printTitle("Relatório de clientes fiéis");
        getOrdemPorClientes().entrySet().forEach(relatorioQuantidadePedidoCliente -> {
            System.out.printf("Nº DE PEDIDOS: %s\nNOME: %s\n", relatorioQuantidadePedidoCliente.getValue(), relatorioQuantidadePedidoCliente.getKey());
        });
    }
}
