package br.com.alura.clientelo.controller.pedido.exception;

import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Produto;

import java.util.List;

public class ItensForaDeEstoqueException extends RuntimeException {
    private List<ItemForaDeEstoque> pedidos;
    public ItensForaDeEstoqueException(List<ItemPedido> pedidosForaEstoque) {
        super("Produto fora de estoque");
        pedidosForaEstoque.forEach(pedido -> pedidos.add(new ItemForaDeEstoque(pedido.getProduto().getNome(), pedido.getProduto().getQtdEstoque())));
    }

    public List<ItemForaDeEstoque> getPedidos() {
        return pedidos;
    }

    public class ItemForaDeEstoque {
        private String nome;
        private Integer quantidade;

        public ItemForaDeEstoque(String nome, Integer quantidade) {

            this.nome = nome;
            this.quantidade = quantidade;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public String getNome() {
            return nome;
        }
    }
}
