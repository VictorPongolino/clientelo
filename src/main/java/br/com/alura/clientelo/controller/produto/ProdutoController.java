package br.com.alura.clientelo.controller.produto;

import br.com.alura.clientelo.dao.ProdutoService;
import br.com.alura.clientelo.modal.Produto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Produto")
@RestController
@RequestMapping(value = "/api/produtos", produces="application/json", consumes="application/json")
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
    @ApiOperation(value = "Cadastra um novo produto")
    public void cadastrar(@RequestBody @Valid CriarProdutoDTO produtoDTO) {
        Produto novoProduto = produtoConverter.converter(produtoDTO);
        produtoService.cadastrar(novoProduto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza os dados de um produto")
    public void atualizarProduto(@PathVariable("id") Long produtoId, @RequestBody @Valid AtualizarProdutoDTO atualizarProduto) {
        produtoService.atualizar(atualizarProdutoToProdutoConverter.convert(produtoId, atualizarProduto));
    }

    @GetMapping
    @ApiOperation(value = "Lista todos os produtos por paginação")
    public Page<ListagemProdutosDTO> listarProdutos(@PageableDefault(size = 5, sort = "nome") Pageable pageable) {
        Page<Produto> produtos = produtoService.listaTodas(pageable);
        return produtos.map(listagemProdutosDTOConverter::converter);
    }

}
