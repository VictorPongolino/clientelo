package br.com.alura.clientelo.controller.pedido;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.clientelo.dao.PedidoService;
import br.com.alura.clientelo.modal.Pedido;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CriarPedidoDtoToPedidoConverter criarPedidoDtoToPedidoConverter;
    private final PedidoService pedidoService;

    public PedidoController(CriarPedidoDtoToPedidoConverter criarPedidoDtoToPedidoConverter, PedidoService pedidoService) {
        this.criarPedidoDtoToPedidoConverter = criarPedidoDtoToPedidoConverter;
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public void criarPedido(@RequestBody @Valid CriarPedidoDTO criarPedidoDTO) {
        Pedido pedido = criarPedidoDtoToPedidoConverter.convert(criarPedidoDTO);
        if (pedido.getItempedidos().stream().anyMatch(itemPedido -> itemPedido.getProduto().isForaDeEstoque())) {
            return;
        }

        pedido.getItempedidos().stream().anyMatch(itemPedido -> itemPedido.getQuantidade() >= 10);
        pedidoService.cadastrar(pedido);
    }


}
