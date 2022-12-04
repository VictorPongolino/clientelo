package br.com.alura.clientelo.controller.produto;

import br.com.alura.clientelo.controller.categoria.CategoriaNaoEncontradaException;
import br.com.alura.clientelo.controller.produto.exception.ProdutoNaoEncontradoException;
import br.com.alura.clientelo.dao.CategoriaService;
import br.com.alura.clientelo.dao.ProdutoService;
import br.com.alura.clientelo.modal.Categoria;
import br.com.alura.clientelo.modal.Produto;
import org.springframework.stereotype.Component;

@Component
public class AtualizarProdutoToProdutoConverter {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public AtualizarProdutoToProdutoConverter(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    public Produto convert(Long produtoId, AtualizarProdutoDTO atualizarProdutoDTO) {
        Produto produto = produtoService.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
        Categoria categoria = categoriaService.findById(atualizarProdutoDTO.getCategoriaId()).orElseThrow(() -> new CategoriaNaoEncontradaException(atualizarProdutoDTO.getCategoriaId()));
        produto.setNome(atualizarProdutoDTO.getNome());
        produto.setPreco(atualizarProdutoDTO.getPreco());
        produto.setDescricao(atualizarProdutoDTO.getDescricao());
        produto.setQtdEstoque(atualizarProdutoDTO.getQuantidade());
        produto.setCategoria(categoria);
        return produto;
    }
}
