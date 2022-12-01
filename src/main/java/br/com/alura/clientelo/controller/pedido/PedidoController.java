package br.com.alura.clientelo.controller.pedido;

import br.com.alura.clientelo.controller.pedido.dto.DetalhePedidoDTO;
import br.com.alura.clientelo.controller.pedido.dto.ListagemPedidosDTO;
import br.com.alura.clientelo.controller.pedido.dto.converter.CriarPedidoDtoToPedidoConverter;
import br.com.alura.clientelo.controller.pedido.dto.converter.PedidoToDetalhePedidoDtoConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.alura.clientelo.controller.pedido.dto.CriarPedidoDTO;
import br.com.alura.clientelo.dao.PedidoService;
import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Pedido;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final CriarPedidoDtoToPedidoConverter criarPedidoDtoToPedidoConverter;
    private final PedidoService pedidoService;
    private final PedidoToDetalhePedidoDtoConverter detalhePedidoConverter;

    public PedidoController(CriarPedidoDtoToPedidoConverter criarPedidoDtoToPedidoConverter, PedidoService pedidoService, PedidoToDetalhePedidoDtoConverter detalhePedidoConverter) {
        this.criarPedidoDtoToPedidoConverter = criarPedidoDtoToPedidoConverter;
        this.pedidoService = pedidoService;
        this.detalhePedidoConverter = detalhePedidoConverter;
    }

    @GetMapping
    public Page<ListagemPedidosDTO> listarPedido(@PageableDefault(size = 5, sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ListagemPedidosDTO> pedidos = pedidoService.findAll(pageable).map(pedido -> {
            BigDecimal valor = pedido.getItempedidos().stream().map(ItemPedido::getMontanteTotal).reduce(BigDecimal.ZERO, (antes, dps) -> antes.add(dps));
            BigDecimal desconto = pedido.getItempedidos().stream().map(ItemPedido::getDesconto).reduce(BigDecimal.ZERO,  (antes, dps) -> antes.add(dps));

            ListagemPedidosDTO.ListagemPedidoCliente cliente = new ListagemPedidosDTO.ListagemPedidoCliente(pedido.getCliente().getId(), pedido.getCliente().getNome());

            return new ListagemPedidosDTO(pedido.getData(), valor, desconto, Long.valueOf(pedido.getItempedidos().size()), cliente);
        });

        return pedidos;
    }

    @GetMapping("/{id}")
    public DetalhePedidoDTO detalhesPedido(@PathVariable("id") Long pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId).orElseThrow();
        return detalhePedidoConverter.converter(pedido);
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
