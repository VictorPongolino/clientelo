package br.com.alura.clientelo.controller.pedido.exception;

import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Produto;

import java.util.List;

public class ItensForaDeEstoqueException extends RuntimeException {
    private List<ItemForaDeEstoque> pedidos;
    public ItensForaDeEstoqueException(List<ItemPedido> pedidosForaEstoque) {
        super("Produto fora de estoque");
        pedidosForaEstoque.forEach(pedido -> pedidos.add(new ItemForaDeEstoque(pedido.getProduto().getId(), pedido.getProduto().getNome(), pedido.getProduto().getQtdEstoque())));
    }

    public List<ItemForaDeEstoque> getPedidos() {
        return pedidos;
    }

    public class ItemForaDeEstoque {
        private Long produtoId;
        private String nome;
        private Integer quantidade;

        public ItemForaDeEstoque(Long produtoId, String nome, Integer quantidade) {
            this.produtoId = produtoId;
            this.nome = nome;
            this.quantidade = quantidade;
        }

        public Long getProdutoId() {
            return produtoId;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return "ItemForaDeEstoque{" +
                    "produtoId=" + produtoId +
                    ", nome='" + nome + '\'' +
                    ", quantidade=" + quantidade +
                    '}';
        }
    }
}
