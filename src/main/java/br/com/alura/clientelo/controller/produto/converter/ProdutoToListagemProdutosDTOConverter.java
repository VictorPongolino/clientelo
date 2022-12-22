package br.com.alura.clientelo.controller.produto.converter;

import br.com.alura.clientelo.controller.produto.dto.ListagemProdutosDTO;
import br.com.alura.clientelo.modal.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoToListagemProdutosDTOConverter {

    public ListagemProdutosDTO converter(Produto produto) {
        return new ListagemProdutosDTO(produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQtdEstoque(),
                Long.valueOf(produto.getCategoria().getId()), produto.getCategoria().getNome());
    }
}
