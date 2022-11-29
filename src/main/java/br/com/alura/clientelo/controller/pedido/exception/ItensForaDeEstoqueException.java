package br.com.alura.clientelo.controller.pedido.exception;

import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Produto;

import java.util.List;

public class ItensForaDeEstoqueException extends RuntimeException {
    private List<ItemForaDeEstoque> pedidos;
    public ItensForaDeEstoqueException(List<ItemPedido> pedidosForaEstoque) {
        pedidosForaEstoque.forEach(pedido -> pedidos.add(new ItemForaDeEstoque(pedido.getProduto().getNome())));
    }

    public class ItemForaDeEstoque {
        private String nome;

        public ItemForaDeEstoque(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }
}
