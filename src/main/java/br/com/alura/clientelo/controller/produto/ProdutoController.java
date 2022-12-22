package br.com.alura.clientelo.controller.produto;

import br.com.alura.clientelo.controller.produto.converter.AtualizarProdutoToProdutoConverter;
import br.com.alura.clientelo.controller.produto.converter.CriarProdutoDtoToProdutoConverter;
import br.com.alura.clientelo.controller.produto.converter.ProdutoToListagemProdutosDTOConverter;
import br.com.alura.clientelo.controller.produto.converter.ProdutoToProdutoCriadoDTOConverter;
import br.com.alura.clientelo.controller.produto.dto.AtualizarProdutoDTO;
import br.com.alura.clientelo.controller.produto.dto.CriarProdutoDTO;
import br.com.alura.clientelo.controller.produto.dto.ListagemProdutosDTO;
import br.com.alura.clientelo.controller.produto.dto.ProdutoCriadoDTO;
import br.com.alura.clientelo.dao.ProdutoService;
import br.com.alura.clientelo.modal.Produto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

@Api(tags = "Produto")
@RestController
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CriarProdutoDtoToProdutoConverter produtoConverter;
    private final ProdutoToListagemProdutosDTOConverter listagemProdutosDTOConverter;
    private final AtualizarProdutoToProdutoConverter atualizarProdutoToProdutoConverter;
    private final ProdutoToProdutoCriadoDTOConverter produtoToProdutoCriadoDTO;
    public ProdutoController(ProdutoService produtoService, CriarProdutoDtoToProdutoConverter produtoConverter, ProdutoToListagemProdutosDTOConverter listagemProdutosDTOConverter, AtualizarProdutoToProdutoConverter atualizarProdutoToProdutoConverter, ProdutoToProdutoCriadoDTOConverter produtoToProdutoCriadoDTO) {
        this.produtoService = produtoService;
        this.produtoConverter = produtoConverter;
        this.listagemProdutosDTOConverter = listagemProdutosDTOConverter;
        this.atualizarProdutoToProdutoConverter = atualizarProdutoToProdutoConverter;
        this.produtoToProdutoCriadoDTO = produtoToProdutoCriadoDTO;
    }

    @PostMapping("/api/produtos")
    @ApiOperation(value = "Cadastra um novo produto")
    public ResponseEntity<ProdutoCriadoDTO> cadastrar(@RequestBody @Valid CriarProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder) {
        Produto novoProduto = produtoConverter.converter(produtoDTO);
        ProdutoCriadoDTO produtoCadastrado = produtoToProdutoCriadoDTO.convert(produtoService.cadastrar(novoProduto));
        URI uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produtoCadastrado.getProdutoId()).toUri();
        return ResponseEntity.created(uri).body(produtoCadastrado);
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
