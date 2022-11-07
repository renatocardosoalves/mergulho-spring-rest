package com.github.renatocardosoalves.algalog.domain.service;

import com.github.renatocardosoalves.algalog.domain.model.Ocorrencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private final CatalogoEntregaService catalogoEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        var entrega = this.catalogoEntregaService.buscar(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }

}
