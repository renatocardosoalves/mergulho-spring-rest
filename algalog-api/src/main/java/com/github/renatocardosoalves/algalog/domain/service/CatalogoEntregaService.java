package com.github.renatocardosoalves.algalog.domain.service;

import com.github.renatocardosoalves.algalog.domain.exception.EntregaNaoEncontradaException;
import com.github.renatocardosoalves.algalog.domain.model.Entrega;
import com.github.renatocardosoalves.algalog.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogoEntregaService {

    private final EntregaRepository entregaRepository;

    public List<Entrega> listar() {
        return this.entregaRepository.findAll();
    }

    public Entrega buscar(Long entregaId) {
        var entrega = this.entregaRepository.findById(entregaId);
        if (entrega.isEmpty()) {
            throw new EntregaNaoEncontradaException(entregaId);
        }
        return entrega.get();
    }

}
