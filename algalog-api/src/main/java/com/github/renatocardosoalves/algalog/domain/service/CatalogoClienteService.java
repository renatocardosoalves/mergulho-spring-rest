package com.github.renatocardosoalves.algalog.domain.service;

import com.github.renatocardosoalves.algalog.domain.exception.ClienteNaoEncontradoException;
import com.github.renatocardosoalves.algalog.domain.model.Cliente;
import com.github.renatocardosoalves.algalog.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CatalogoClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        if (Objects.isNull(cliente.getClienteId())) {
            return this.clienteRepository.save(cliente);
        }
        var clienteAtual = this.buscar(cliente.getClienteId());
        clienteAtual.setNome(cliente.getNome());
        clienteAtual.setEmail(cliente.getEmail());
        clienteAtual.setTelefone(cliente.getTelefone());
        return clienteAtual;
    }

    @Transactional
    public void excluir(Long clienteId) {
        var cliente = this.buscar(clienteId);
        this.clienteRepository.delete(cliente);
    }

    public Cliente buscar(Long clienteId) {
        var cliente = this.clienteRepository.findById(clienteId);
        if (cliente.isEmpty()) {
            throw new ClienteNaoEncontradoException(clienteId);
        }
        return cliente.get();
    }

    public List<Cliente> listar() {
        return this.clienteRepository.findAll();
    }

}
