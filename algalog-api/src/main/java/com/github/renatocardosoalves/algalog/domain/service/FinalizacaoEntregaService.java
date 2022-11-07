package com.github.renatocardosoalves.algalog.domain.service;

import com.github.renatocardosoalves.algalog.domain.repository.EntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private final CatalogoEntregaService catalogoEntregaService;
    private final EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long entregaId) {
        var entrega = this.catalogoEntregaService.buscar(entregaId);
        entrega.finalizar();
        this.entregaRepository.save(entrega);
    }

}
