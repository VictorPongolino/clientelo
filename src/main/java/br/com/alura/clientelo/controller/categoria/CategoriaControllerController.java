package br.com.alura.clientelo.controller.categoria;

import br.com.alura.clientelo.dao.CategoriaService;

import br.com.alura.clientelo.modal.Categoria;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.alura.clientelo.modal.Categoria.Status.ATIVA;
import static br.com.alura.clientelo.modal.Categoria.Status.INATIVA;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaControllerController {
    private final CategoriaService categoriaService;
    private final CadastrarCategoriaDtoToCategoria cadastrarCategoriaConverter;

    public CategoriaControllerController(CategoriaService categoriaService, CadastrarCategoriaDtoToCategoria converter) {
        this.categoriaService = categoriaService;
        this.cadastrarCategoriaConverter = converter;
    }

    @PatchMapping("/api/categorias/{id}")
    public void atualizarStatusCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id).orElseThrow();
        categoria.setStatus(ATIVA.equals(categoria.getStatus()) ? INATIVA : ATIVA);
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid CadastrarCategoriaDTO categoriaDTO) {
        categoriaService.cadastrar(cadastrarCategoriaConverter.converter(categoriaDTO));
    }
}
