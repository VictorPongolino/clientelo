package br.com.alura.clientelo.controller.produto;

import br.com.alura.clientelo.dao.CategoriaService;
import br.com.alura.clientelo.modal.Categoria;
import br.com.alura.clientelo.modal.Produto;
import org.springframework.stereotype.Component;

@Component
public class CriarProdutoDtoToProdutoConverter {

    private final CategoriaService categoriaService;

    public CriarProdutoDtoToProdutoConverter(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Produto converter(CriarProdutoDTO produtoDTO){
        Categoria categoria = categoriaService.buscarPorId(produtoDTO.getCategoriaId()).orElseThrow();
        return new Produto(produtoDTO.getNomeProduto(),
                produtoDTO.getDescricao(),
                produtoDTO.getQuantidadeEstoque(),
                produtoDTO.getPreco(),
                categoria);
    }
}
