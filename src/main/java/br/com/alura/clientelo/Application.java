package br.com.alura.clientelo;

import br.com.alura.clientelo.dao.relatorio.OperacaoPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final OperacaoPedidoService operacaoPedidoDAO;

    public Application(OperacaoPedidoService operacaoPedidoDAO) {
        this.operacaoPedidoDAO = operacaoPedidoDAO;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printReportsTests() {
        return (args) -> {
            operacaoPedidoDAO.getVendasPorCategoria().forEach(System.out::println);
            operacaoPedidoDAO.getProdutosTOP3().forEach(System.out::println);
            operacaoPedidoDAO.getClienteFiel().forEach(System.out::println);
            operacaoPedidoDAO.getClientesLucrativos().forEach(System.out::println);
        };
    }
}
