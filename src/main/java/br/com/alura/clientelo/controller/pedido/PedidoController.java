package br.com.alura.clientelo.controller.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alura.clientelo.dao.PedidoService;
import br.com.alura.clientelo.modal.ItemPedido;
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
        Optional<ItemPedido> itemPedidoSemEstoque = pedido.getItempedidos().stream().filter(itemPedido -> itemPedido.getProduto().isForaDeEstoque()).findFirst();
        if (itemPedidoSemEstoque.isPresent()) {
            Long produtoId = itemPedidoSemEstoque.get().getProduto().getId();
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, String.format("Produto Id %d esta fora de estoque!", produtoId));
        }

        ArrayList<ItemPedido> itensPedidos = new ArrayList<>();
        List<ItemPedido> itensPromocionaveis = pedido.getItempedidos().stream()
                .filter(item -> item.getQuantidade() >= 10)
                .collect(Collectors.toList());
        itensPromocionaveis.forEach(item -> {
            item.setTipoDesconto(ItemPedido.TipoDesconto.QUANTIDADE);
            BigDecimal desconto = new BigDecimal("0.10").multiply(item.getDesconto());
            item.setDesconto(item.getDesconto().subtract(desconto));
        });

        if(pedidoService.getTotalPedidosByClienteId(pedido.getCliente().getId()) >= 5) {
            BigDecimal desconto = pedido.getDescontoTotal().multiply(new BigDecimal("0.05"));
            pedido.setDescontoTotal(pedido.getDescontoTotal().add(desconto));
            pedido.setTipoDesconto(Pedido.TipoDesconto.FIDELIDADE);
        }

        pedidoService.cadastrar(pedido);
    }
}
