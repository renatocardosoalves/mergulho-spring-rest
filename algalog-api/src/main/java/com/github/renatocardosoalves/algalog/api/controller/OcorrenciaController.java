package com.github.renatocardosoalves.algalog.api.controller;

import com.github.renatocardosoalves.algalog.api.assembler.OcorrenciaAssembler;
import com.github.renatocardosoalves.algalog.api.model.request.OcorrenciaRequest;
import com.github.renatocardosoalves.algalog.api.model.response.OcorrenciaResponse;
import com.github.renatocardosoalves.algalog.domain.service.CatalogoEntregaService;
import com.github.renatocardosoalves.algalog.domain.service.RegistroOcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private final RegistroOcorrenciaService registroOcorrenciaService;
    private final CatalogoEntregaService catalogoEntregaService;
    private final OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponse registrar(@PathVariable Long entregaId, @RequestBody @Valid OcorrenciaRequest request) {
        return this.ocorrenciaAssembler.toModel(this.registroOcorrenciaService.registrar(entregaId, request.getDescricao()));
    }

    @GetMapping
    public List<OcorrenciaResponse> listar(@PathVariable Long entregaId) {
        var entrega = this.catalogoEntregaService.buscar(entregaId);
        return this.ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }

}
