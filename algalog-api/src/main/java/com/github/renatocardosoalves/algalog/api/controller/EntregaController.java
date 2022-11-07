package com.github.renatocardosoalves.algalog.api.controller;

import com.github.renatocardosoalves.algalog.api.assembler.EntregaAssembler;
import com.github.renatocardosoalves.algalog.api.model.response.EntregaResponse;
import com.github.renatocardosoalves.algalog.api.model.request.EntregaRequest;
import com.github.renatocardosoalves.algalog.domain.service.CatalogoEntregaService;
import com.github.renatocardosoalves.algalog.domain.service.FinalizacaoEntregaService;
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
    private final FinalizacaoEntregaService finalizacaoEntregaService;
    private final EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponse solicitar(@RequestBody @Valid EntregaRequest request) {
        var entregaSolicitada = this.entregaAssembler.toEntity(request);
        return this.entregaAssembler.toModel(this.solicitacaoEntregaService.solicitar(entregaSolicitada));
    }

    @GetMapping
    public List<EntregaResponse> listar() {
        return this.entregaAssembler.toCollectionModel(this.catalogoEntregaService.listar());
    }

    @GetMapping("/{entregaId}")
    public EntregaResponse buscar(@PathVariable Long entregaId) {
        return this.entregaAssembler.toModel(this.catalogoEntregaService.buscar(entregaId));
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        this.finalizacaoEntregaService.finalizar(entregaId);
    }

}
