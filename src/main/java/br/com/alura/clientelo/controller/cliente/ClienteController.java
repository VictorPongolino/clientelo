package br.com.alura.clientelo.controller.cliente;

import br.com.alura.clientelo.controller.cliente.dto.converter.ClienteToCadastroClienteConverter;
import br.com.alura.clientelo.controller.cliente.vo.converter.CadastroClienteDtoTOClienteConverter;
import br.com.alura.clientelo.controller.cliente.vo.converter.ClienteToListagemClienteDtoConverter;
import br.com.alura.clientelo.controller.cliente.vo.CadastroClienteVO;
import br.com.alura.clientelo.controller.cliente.vo.ListagemClienteVO;
import br.com.alura.clientelo.modal.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.clientelo.dao.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(tags = "Cliente")
@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteToListagemClienteDtoConverter listagemClienteConverter;
    private final CadastroClienteDtoTOClienteConverter cadastroClienteDtoTOClienteConverter;
    private final ClienteToCadastroClienteConverter clienteToCadastroClienteConverter;

    public ClienteController(ClienteService clienteService, ClienteToListagemClienteDtoConverter listagemClienteConverter, CadastroClienteDtoTOClienteConverter cadastroClienteDtoTOClienteConverter, ClienteToCadastroClienteConverter clienteToCadastroClienteConverter) {
        this.clienteService = clienteService;
        this.listagemClienteConverter = listagemClienteConverter;
        this.cadastroClienteDtoTOClienteConverter = cadastroClienteDtoTOClienteConverter;
        this.clienteToCadastroClienteConverter = clienteToCadastroClienteConverter;
    }

    @GetMapping
    @ApiOperation(value = "Lista todos os clientes por paginação")
    public Page<ListagemClienteVO> listagemClientes(@PageableDefault(size = 5, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return clienteService.findAll(pageable).map(listagemClienteConverter::convert);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um cliente")
    public ResponseEntity<CadastroClienteVO> cadastrar(@RequestBody @Valid CadastroClienteVO cadastroClienteDTO) {
        Cliente cliente = cadastroClienteDtoTOClienteConverter.convert(cadastroClienteDTO);
        Cliente clienteCadastrado = clienteService.cadastrar(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCadastrado.getId()).toUri();
        return ResponseEntity.created(location).body(clienteToCadastroClienteConverter.convert(clienteCadastrado));
    }

}
