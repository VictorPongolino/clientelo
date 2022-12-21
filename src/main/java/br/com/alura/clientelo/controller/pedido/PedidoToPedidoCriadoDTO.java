package br.com.alura.clientelo.controller.pedido;

import br.com.alura.clientelo.modal.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoToPedidoCriadoDTO {
    public PedidoCriadoDTO convert(Pedido pedidoCadastrado) {
        Long id = pedidoCadastrado.getId();
        Long clienteId = pedidoCadastrado.getCliente().getId();
        LocalDate data = pedidoCadastrado.getData();
        BigDecimal descontoTotal = pedidoCadastrado.getDescontoTotal();
        String tipoDescontoPedido = pedidoCadastrado.getTipoDesconto().name();

        List<PedidoCriadoDTO.ItemPedidoCriadoDTO> itensPedidos = new ArrayList<>();
        pedidoCadastrado.getItempedidos().forEach(itemPedido -> {
            BigDecimal precoUnitario = itemPedido.getPrecoUnitario();
            Integer quantidade = itemPedido.getQuantidade();
            Long produtoId = itemPedido.getProduto().getId();
            BigDecimal desconto = itemPedido.getDesconto();
            String tipoDesconto = itemPedido.getTipoDesconto().name();
            itensPedidos.add(new PedidoCriadoDTO.ItemPedidoCriadoDTO(precoUnitario, quantidade, produtoId, desconto, tipoDesconto));
        });

        return new PedidoCriadoDTO(id, itensPedidos, clienteId, data, descontoTotal, tipoDescontoPedido);
    }
}
