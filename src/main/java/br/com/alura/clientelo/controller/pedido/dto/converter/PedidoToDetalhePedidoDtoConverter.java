package br.com.alura.clientelo.controller.pedido.dto.converter;

import br.com.alura.clientelo.controller.pedido.dto.DetalhePedidoVO;
import br.com.alura.clientelo.controller.pedido.dto.DetalhePedidoVO.ClienteDetalhePedidoDTO;
import br.com.alura.clientelo.controller.pedido.dto.DetalhePedidoVO.DetalheProdutoPedidoDTO;
import br.com.alura.clientelo.dao.PedidoService;
import br.com.alura.clientelo.modal.cliente.Cliente;
import br.com.alura.clientelo.modal.pedido.ItemPedido;
import br.com.alura.clientelo.modal.Produto;
import br.com.alura.clientelo.modal.pedido.Pedido;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PedidoToDetalhePedidoDtoConverter {

    private final PedidoService pedidoService;

    public PedidoToDetalhePedidoDtoConverter(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public DetalhePedidoVO converter(Pedido pedido) {
        ClienteDetalhePedidoDTO cliente = converterClienteToDTO(pedido.getCliente());
        Set<ItemPedido> itempedidos = Set.copyOf(pedido.getItempedidos());

        return new DetalhePedidoVO(pedido.getData(), pedido.getPrecoFinal(), pedido.getDescontoTotal(), converterItensPedidoToDTO(itempedidos), cliente);
    }

    private ClienteDetalhePedidoDTO converterClienteToDTO(Cliente cliente) {
        return new ClienteDetalhePedidoDTO(cliente.getId(), cliente.getNome());
    }

    private Set<DetalheProdutoPedidoDTO> converterItensPedidoToDTO(Set<ItemPedido> itemPedido) {
        return itemPedido.stream().map(this::converterItemPedidoToDTO).collect(Collectors.toSet());
    }

    private DetalheProdutoPedidoDTO converterItemPedidoToDTO(ItemPedido itemPedido) {
        Produto produto = itemPedido.getProduto();
        return new DetalheProdutoPedidoDTO(produto.getId(),
                produto.getNome(), produto.getCategoria().getNome(),
                produto.getQtdEstoque(), itemPedido.getPrecoUnitario(),
                itemPedido.getPrecoFinal(), itemPedido.getDesconto());
    }
}
