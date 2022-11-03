package br.com.alura.clientelo.reports.relatorios;

import br.com.alura.clientelo.reports.PedidoFake;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RelatorioQuantidadePorClienteTest {

    private static final String NOME_CLIENTE_UNICO = "ANA";
    private final RelatorioQuantidadePorCliente subject = new RelatorioQuantidadePorCliente(PedidoFake.getPedidos());

    @Test
    public void validarClientesFieisSeRetornaApenasUmCliente() {
        Map<String, Integer> clientesFieis = subject.getOrdemPorClientes();
        Map.Entry<String, Integer> cliente = clientesFieis.entrySet().stream()
                .filter(nomeCliente -> nomeCliente.getKey().equals(NOME_CLIENTE_UNICO))
                .limit(3)
                .findFirst().get();

        assertEquals(NOME_CLIENTE_UNICO, cliente.getKey());
        assertEquals(4, cliente.getValue());
    }

}