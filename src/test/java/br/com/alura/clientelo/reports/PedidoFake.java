package br.com.alura.clientelo.reports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.clientelo.Pedido;

public class PedidoFake {

    private static List<Pedido> pedidos = new ArrayList<>();

    static {
        pedidos.add(new Pedido("INFORMÁTICA", "Notebook Samsung", "ANA", new BigDecimal("3523.00"), 1, LocalDate.of(2022, 1, 1)));
        pedidos.add(new Pedido("MÓVEIS", "Sofá 3 lugares", "ANA", new BigDecimal("2500.00"), 1, LocalDate.of(2022, 1, 5)));
        pedidos.add(new Pedido("LIVROS", "Clean Architecture", "ANA", new BigDecimal("102.90"), 2, LocalDate.of(2022, 1, 8)));
        pedidos.add(new Pedido("MÓVEIS", "Mesa de jantar 6 lugares", "ELI", new BigDecimal("3678.98"), 1, LocalDate.of(2022, 1, 6)));
        pedidos.add(new Pedido("CELULARES", "iPhone 13 Pro", "ANA", new BigDecimal("9176.00"), 6, LocalDate.of(2022, 1, 13)));
        pedidos.add(new Pedido("INFORMÁTICA", "\"Monitor Dell 27\"\"\"", "DANI", new BigDecimal("1889.00"), 3, LocalDate.of(2022, 1, 4)));
        pedidos.add(new Pedido("LIVROS", "Implementing Domain-Driven Design", "GABI", new BigDecimal("144.07"), 3, LocalDate.of(2022, 1, 10)));
        pedidos.add(new Pedido("AUTOMOTIVA", "Jogo de pneus", "BIA", new BigDecimal("1276.79"), 1, LocalDate.of(2022, 1, 15)));
        pedidos.add(new Pedido("LIVROS", "Clean Code", "BIA", new BigDecimal("95.17"), 1, LocalDate.of(2022, 1, 9)));
        pedidos.add(new Pedido("CELULARES", "Galaxy S22 Ultra", "DANI", new BigDecimal("8549.10"), 5, LocalDate.of(2022, 1, 14)));
        pedidos.add(new Pedido("INFORMÁTICA", "Macbook Pro 16", "CAIO", new BigDecimal("31752.00"), 1, LocalDate.of(2022, 1, 3)));
        pedidos.add(new Pedido("LIVROS", "Refactoring Improving the Design of Existing Code", "DANI", new BigDecimal("173.90"), 1, LocalDate.of(2022, 1, 12)));
        pedidos.add(new Pedido("MÓVEIS", "Cama queen size", "DANI", new BigDecimal("3100.00"), 2, LocalDate.of(2022, 1, 7)));
        pedidos.add(new Pedido("AUTOMOTIVA", "Central multimidia", "CAIO", new BigDecimal("711.18"), 1, LocalDate.of(2022, 1, 16)));
        pedidos.add(new Pedido("LIVROS", "Building Microservices", "CAIO", new BigDecimal("300.28"), 2, LocalDate.of(2022, 1, 11)));
        pedidos.add(new Pedido("INFORMÁTICA", "Galaxy Tab S8", "BIA", new BigDecimal("5939.10"), 4, LocalDate.of(2022, 1, 2)));
    }


    public static List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }
}
