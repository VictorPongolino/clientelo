package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.modal.Cliente;
import br.com.alura.clientelo.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> buscarPorId(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
    @Transactional
    public Cliente cadastrar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void remover(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
    public Page<Cliente> listaTodas(Pageable pagina) {
        return this.clienteRepository.findAll(pagina);
    }

    public Page<Cliente> listaPorNome(String nome, Pageable pageable) {
        return this.clienteRepository.findByNome(nome, pageable);
    }

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }
}
