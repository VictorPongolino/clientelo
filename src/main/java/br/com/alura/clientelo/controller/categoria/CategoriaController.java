package br.com.alura.clientelo.controller.categoria;

import br.com.alura.clientelo.controller.categoria.dto.CadastrarCategoriaDTO;
import br.com.alura.clientelo.controller.categoria.dto.converter.CadastrarCategoriaDtoToCategoriaConverter;
import br.com.alura.clientelo.controller.categoria.exception.CategoriaNaoEncontradaException;
import br.com.alura.clientelo.controller.categoria.vo.CategoriaCriadaVO;
import br.com.alura.clientelo.dao.CategoriaService;
import br.com.alura.clientelo.dao.relatorio.OperacaoPedidoService;
import br.com.alura.clientelo.dao.relatorio.RelatorioVendasCategoriaVO;
import br.com.alura.clientelo.modal.Categoria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.Cacheable;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static br.com.alura.clientelo.modal.Categoria.Status.ATIVA;
import static br.com.alura.clientelo.modal.Categoria.Status.INATIVA;

@Api(tags = "Categoria")
@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final OperacaoPedidoService operacaoPedidoService;

    private final CadastrarCategoriaDtoToCategoriaConverter cadastrarCategoriaConverter;

    public CategoriaController(CategoriaService categoriaService, OperacaoPedidoService operacaoPedidoService, CadastrarCategoriaDtoToCategoriaConverter converter) {
        this.categoriaService = categoriaService;
        this.operacaoPedidoService = operacaoPedidoService;
        this.cadastrarCategoriaConverter = converter;
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Atualiza o status de uma categoria para ATIVA ou INATIVA")
    public void atualizarStatusCategoria(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        categoria.setStatus(ATIVA.equals(categoria.getStatus()) ? INATIVA : ATIVA);
    }

    @Cacheable("vendas")
    @GetMapping("/vendas")
    public List<RelatorioVendasCategoriaVO> relatorioVendas() {
        return operacaoPedidoService.getVendasPorCategoria();
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma categoria")
    public ResponseEntity<CategoriaCriadaVO> cadastrar(@RequestBody @Valid CadastrarCategoriaDTO categoriaDTO) {
        Categoria categoriaCadastrada = categoriaService.cadastrar(cadastrarCategoriaConverter.converter(categoriaDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(categoriaCadastrada.getId()).toUri();
        return ResponseEntity.created(location).body(new CategoriaCriadaVO(categoriaCadastrada.getId(), categoriaCadastrada.getNome()));
    }
}
