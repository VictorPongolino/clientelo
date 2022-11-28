package br.com.alura.clientelo.controller.categoria;

import br.com.alura.clientelo.dao.CategoriaService;
import br.com.alura.clientelo.modal.Categoria;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final CadastrarCategoriaDtoToCategoria cadastrarCategoriaConverter;

    public CategoriaController(CategoriaService categoriaService, CadastrarCategoriaDtoToCategoria converter) {
        this.categoriaService = categoriaService;
        this.cadastrarCategoriaConverter = converter;
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid CadastrarCategoriaDTO categoriaDTO) {
        categoriaService.cadastrar(cadastrarCategoriaConverter.converter(categoriaDTO));
    }
}
