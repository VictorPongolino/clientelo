package br.com.alura.clientelo;

import br.com.alura.clientelo.modal.Pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessadorDeCsv {

    public static Pedido[] processaArquivo(String nomeDoArquivo) {
        try {
            URL recursoCSV = ClassLoader.getSystemResource(nomeDoArquivo);
            Path caminhoDoArquivo = Path.of(recursoCSV.toURI());

            List<Pedido> pedidos = new ArrayList<>();
            try (Scanner leitorDeLinhas = new Scanner(caminhoDoArquivo)) {
            	leitorDeLinhas.nextLine();

                while (leitorDeLinhas.hasNextLine()) {
                    String[] registro = leitorDeLinhas.nextLine().split(",");
                    pedidos.add(from(registro));
                }
            }
            
            return pedidos.toArray(new Pedido[0]);
        } catch (URISyntaxException e) {
            throw new RuntimeException(String.format("Arquivo {} não localizado!", nomeDoArquivo));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir Scanner para processar arquivo!");
        }
    }
    
    private static Pedido from(String[] registro) {
    	String categoria = registro[0];
        String produto = registro[1];
        BigDecimal preco = new BigDecimal(registro[2]);
        int quantidade = Integer.parseInt(registro[3]);
        LocalDate data = LocalDate.parse(registro[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String cliente = registro[5];
        
        return new Pedido(categoria, produto, cliente, preco, quantidade, data);
    }
}
