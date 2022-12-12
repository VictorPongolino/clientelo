package br.com.alura.clientelo.controller.pedido;

import br.com.alura.clientelo.controller.pedido.dto.DetalhePedidoVO;
import br.com.alura.clientelo.controller.pedido.dto.ListagemPedidosVO;
import br.com.alura.clientelo.controller.pedido.dto.converter.CriarPedidoDtoToPedidoConverter;
import br.com.alura.clientelo.controller.pedido.dto.converter.PedidoToDetalhePedidoDtoConverter;
import br.com.alura.clientelo.controller.pedido.exception.ItensForaDeEstoqueException;
import br.com.alura.clientelo.controller.pedido.exception.PedidoNaoExisteException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.clientelo.controller.pedido.dto.CriarPedidoDTO;
import br.com.alura.clientelo.dao.PedidoService;
import br.com.alura.clientelo.modal.ItemPedido;
import br.com.alura.clientelo.modal.Pedido;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
@Api(tags = "Pedido")
@RestController
@RequestMapping(name = "/api/pedidos", produces="application/json", consumes="application/json")
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
    @ApiOperation(value = "Listar todos os pedidos por paginação")
    public Page<ListagemPedidosVO> listarPedido(@PageableDefault(size = 5, sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ListagemPedidosVO> pedidos = pedidoService.findAll(pageable).map(pedido -> {
            BigDecimal valor = pedido.getItempedidos().stream().map(ItemPedido::getMontanteTotal).reduce(BigDecimal.ZERO, (antes, dps) -> antes.add(dps));
            BigDecimal desconto = pedido.getItempedidos().stream().map(ItemPedido::getDesconto).reduce(BigDecimal.ZERO,  (antes, dps) -> antes.add(dps));

            ListagemPedidosVO.ListagemPedidoCliente cliente = new ListagemPedidosVO.ListagemPedidoCliente(pedido.getCliente().getId(), pedido.getCliente().getNome());

            return new ListagemPedidosVO(pedido.getData(), valor, desconto, Long.valueOf(pedido.getItempedidos().size()), cliente);
        });

        return pedidos;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar um pedido por ID")
    public DetalhePedidoVO detalhesPedido(@PathVariable("id") Long pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId).orElseThrow(() -> new PedidoNaoExisteException(pedidoId));
        return detalhePedidoConverter.converter(pedido);
    }


    @PostMapping
    @ApiOperation(value = "Criar um pedido")
    public void criarPedido(@RequestBody @Valid CriarPedidoDTO criarPedidoDTO) {
        Pedido pedido = criarPedidoDtoToPedidoConverter.convert(criarPedidoDTO);
        List<ItemPedido> itemPedidoSemEstoque = pedido.getItempedidos().stream().filter(itemPedido -> itemPedido.getProduto().isForaDeEstoque()).collect(Collectors.toList());
        if (!itemPedidoSemEstoque.isEmpty()) {
            throw new ItensForaDeEstoqueException(itemPedidoSemEstoque);
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
