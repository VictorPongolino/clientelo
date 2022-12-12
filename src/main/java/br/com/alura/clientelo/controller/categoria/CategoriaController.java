package br.com.alura.clientelo.controller.categoria;

import br.com.alura.clientelo.dao.CategoriaService;

import br.com.alura.clientelo.modal.Categoria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.alura.clientelo.modal.Categoria.Status.ATIVA;
import static br.com.alura.clientelo.modal.Categoria.Status.INATIVA;

@Api(tags = "Categoria")
@RestController
@RequestMapping(name = "/api/categorias", produces="application/json", consumes="application/json")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final CadastrarCategoriaDtoToCategoria cadastrarCategoriaConverter;

    public CategoriaController(CategoriaService categoriaService, CadastrarCategoriaDtoToCategoria converter) {
        this.categoriaService = categoriaService;
        this.cadastrarCategoriaConverter = converter;
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Atualiza o status de uma categoria para ATIVA ou INATIVA")
    public void atualizarStatusCategoria(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        categoria.setStatus(ATIVA.equals(categoria.getStatus()) ? INATIVA : ATIVA);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma categoria")
    public void cadastrar(@RequestBody @Valid CadastrarCategoriaDTO categoriaDTO) {
        categoriaService.cadastrar(cadastrarCategoriaConverter.converter(categoriaDTO));
    }
}
