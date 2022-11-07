package com.github.renatocardosoalves.algalog.domain.service;

import com.github.renatocardosoalves.algalog.domain.exception.ClienteNaoEncontradoException;
import com.github.renatocardosoalves.algalog.domain.exception.NegocioException;
import com.github.renatocardosoalves.algalog.domain.model.Entrega;
import com.github.renatocardosoalves.algalog.domain.model.StatusEntrega;
import com.github.renatocardosoalves.algalog.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        var clienteDaEntrega = entrega.getCliente();
        try {
            clienteDaEntrega = this.catalogoClienteService.buscar(clienteDaEntrega.getClienteId());
        } catch (ClienteNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage());
        }
        entrega.setCliente(clienteDaEntrega);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());
        return this.entregaRepository.save(entrega);
    }

}
