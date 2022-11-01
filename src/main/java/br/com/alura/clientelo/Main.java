package br.com.alura.clientelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import br.com.alura.clientelo.reports.RelatorioHelper;
import br.com.alura.clientelo.reports.relatorios.RelatorioClientesLucrativos;
import br.com.alura.clientelo.reports.relatorios.RelatorioProdutoesCarosCategoria;
import br.com.alura.clientelo.reports.relatorios.RelatorioProdutosVendidos;
import br.com.alura.clientelo.reports.relatorios.RelatorioQuantidadePorCliente;
import br.com.alura.clientelo.reports.relatorios.RelatorioVendasCategoria;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Pedido[] pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");


        
        List<Pedido> todosPedidos = Arrays.asList(pedidos);
        RelatorioHelper.mostrar(new RelatorioVendasCategoria(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioProdutosVendidos(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioVendasCategoria(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioProdutoesCarosCategoria(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioQuantidadePorCliente(todosPedidos));
        RelatorioHelper.mostrar(new RelatorioClientesLucrativos(todosPedidos));
    }
}
