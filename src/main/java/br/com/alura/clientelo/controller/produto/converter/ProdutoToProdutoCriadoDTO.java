package br.com.alura.clientelo.controller.produto.converter;

import br.com.alura.clientelo.controller.produto.ProdutoCriadoDTO;
import br.com.alura.clientelo.modal.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProdutoToProdutoCriadoDTO {
    public ProdutoCriadoDTO convert(Produto cadastrar) {
        Long produtoId = cadastrar.getId();
        String nomeProduto = cadastrar.getNome();
        BigDecimal preco = cadastrar.getPreco();
        String descricao = cadastrar.getDescricao();
        Integer quantidadeEstoque = cadastrar.getQtdEstoque();
        Integer categoriaId = cadastrar.getCategoria().getId();
        return new ProdutoCriadoDTO(produtoId, nomeProduto, preco, descricao, quantidadeEstoque, categoriaId);
    }
}
