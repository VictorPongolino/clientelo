package br.com.alura.clientelo;

import br.com.alura.clientelo.dao.relatorio.OperacaoPedidoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        printTest();
    }

    private static void printTest() {
        OperacaoPedidoDAO operacaoPedidoDAO = new OperacaoPedidoDAO();

        operacaoPedidoDAO.getVendasPorCategoria().forEach(System.out::println);
        operacaoPedidoDAO.getProdutosTOP3().forEach(System.out::println);
        operacaoPedidoDAO.getClienteFiel().forEach(System.out::println);
        operacaoPedidoDAO.getClientesLucrativos().forEach(System.out::println);
    }
}
