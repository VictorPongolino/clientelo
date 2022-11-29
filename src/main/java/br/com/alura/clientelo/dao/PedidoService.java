package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Pedido;
import br.com.alura.clientelo.repository.PedidoRepository;
import jakarta.transaction.Transactional;

public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido cadastrar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Long getTotalPedidosByClienteId(Long id) {
        return pedidoRepository.countByClienteId(id);
    }
}
