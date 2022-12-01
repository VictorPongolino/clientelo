package br.com.alura.clientelo.controller.produto;

import br.com.alura.clientelo.dao.ProdutoService;
import br.com.alura.clientelo.modal.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CriarProdutoDtoToProdutoConverter produtoConverter;
    private final ProdutoToListagemProdutosDTOConverter listagemProdutosDTOConverter;
    private final AtualizarProdutoToProdutoConverter atualizarProdutoToProdutoConverter;
    public ProdutoController(ProdutoService produtoService, CriarProdutoDtoToProdutoConverter produtoConverter, ProdutoToListagemProdutosDTOConverter listagemProdutosDTOConverter, AtualizarProdutoToProdutoConverter atualizarProdutoToProdutoConverter) {
        this.produtoService = produtoService;
        this.produtoConverter = produtoConverter;
        this.listagemProdutosDTOConverter = listagemProdutosDTOConverter;
        this.atualizarProdutoToProdutoConverter = atualizarProdutoToProdutoConverter;
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid CriarProdutoDTO produtoDTO) {
        Produto novoProduto = produtoConverter.converter(produtoDTO);
        produtoService.cadastrar(novoProduto);
    }

    @PutMapping("/{id}")
    public void atualizarProduto(@PathVariable("id") Long produtoId, @RequestBody @Valid AtualizarProdutoDTO atualizarProduto) {
        produtoService.atualizar(atualizarProdutoToProdutoConverter.convert(produtoId, atualizarProduto));
    }

    @GetMapping
    public Page<ListagemProdutosDTO> listarProdutos(@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
        Page<Produto> produtos = produtoService.listaTodas(pageable);
        return produtos.map(listagemProdutosDTOConverter::converter);
    }

}
