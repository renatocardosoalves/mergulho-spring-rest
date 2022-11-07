package com.github.renatocardosoalves.algalog.api.controller;

import com.github.renatocardosoalves.algalog.domain.model.Entrega;
import com.github.renatocardosoalves.algalog.domain.service.SolicitacaoEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final SolicitacaoEntregaService solicitacaoEntregaService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody Entrega entrega) {
        return this.solicitacaoEntregaService.solicitar(entrega);
    }

}
