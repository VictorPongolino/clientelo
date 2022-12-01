package br.com.alura.clientelo.controller.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.clientelo.dao.ClienteService;
import br.com.alura.clientelo.modal.Cliente;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteToListagemClienteDtoConverter listagemClienteConverter;
    private final CadastroClienteDtoTOClienteConverter cadastroClienteDtoTOClienteConverter;

    public ClienteController(ClienteService clienteService, ClienteToListagemClienteDtoConverter listagemClienteConverter, CadastroClienteDtoTOClienteConverter cadastroClienteDtoTOClienteConverter) {
        this.clienteService = clienteService;
        this.listagemClienteConverter = listagemClienteConverter;
        this.cadastroClienteDtoTOClienteConverter = cadastroClienteDtoTOClienteConverter;
    }

    @GetMapping
    public Page<ListagemClienteDTO> listagemClientes(@PageableDefault(size = 5, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return clienteService.findAll(pageable).map(listagemClienteConverter::convert);
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid CadastroClienteDTO cadastroClienteDTO) {
        Cliente cliente = cadastroClienteDtoTOClienteConverter.convert(cadastroClienteDTO);
        clienteService.cadastrar(cliente);
    }

}
