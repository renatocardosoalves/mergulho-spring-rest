package com.github.renatocardosoalves.algalog.api.controller;

import com.github.renatocardosoalves.algalog.domain.model.Entrega;
import com.github.renatocardosoalves.algalog.domain.service.CatalogoEntregaService;
import com.github.renatocardosoalves.algalog.domain.service.SolicitacaoEntregaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final CatalogoEntregaService catalogoEntregaService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@RequestBody @Valid Entrega entrega) {
        return this.solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return this.catalogoEntregaService.listar();
    }

    @GetMapping("/{entregaId}")
    public Entrega buscar(@PathVariable Long entregaId) {
        return this.catalogoEntregaService.buscar(entregaId);
    }

}
