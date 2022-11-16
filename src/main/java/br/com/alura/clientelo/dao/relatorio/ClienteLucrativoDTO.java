package br.com.alura.clientelo.dao.relatorio;

import java.math.BigDecimal;

public class ClienteLucrativoDTO {
    private final String nome;
    private final Long quantidadePedidos;
    private final BigDecimal montanteTotal;

    public ClienteLucrativoDTO(String nome, Long quantidadePedidos, BigDecimal montanteTotal) {
        this.nome = nome;
        this.quantidadePedidos = quantidadePedidos;
        this.montanteTotal = montanteTotal;
    }

    public String getNome() {
        return nome;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public BigDecimal getMontanteTotal() {
        return montanteTotal;
    }

    @Override
    public String toString() {
        return "ClienteLucrativoDTO{" +
                "nome='" + nome + '\'' +
                ", quantidadePedidos=" + quantidadePedidos +
                ", montanteTotal=" + montanteTotal +
                '}';
    }
}
