package com.github.renatocardosoalves.algalog.api.controller;

import com.github.renatocardosoalves.algalog.api.assembler.ClienteAssembler;
import com.github.renatocardosoalves.algalog.api.model.request.ClienteRequest;
import com.github.renatocardosoalves.algalog.api.model.response.ClienteResponse;
import com.github.renatocardosoalves.algalog.domain.service.CatalogoClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CatalogoClienteService catalogoClienteService;
    private final ClienteAssembler clienteAssembler;

    @GetMapping
    public List<ClienteResponse> listar() {
        return this.clienteAssembler.toCollectionModel(this.catalogoClienteService.listar());
    }

    @GetMapping("/{clienteId}")
    public ClienteResponse buscar(@PathVariable Long clienteId) {
        return this.clienteAssembler.toModel(this.catalogoClienteService.buscar(clienteId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse adicionar(@RequestBody @Valid ClienteRequest request) {
        var novoCliente = this.clienteAssembler.toEntity(request);
        return this.clienteAssembler.toModel(this.catalogoClienteService.salvar(novoCliente));
    }

    @PutMapping("/{clienteId}")
    public ClienteResponse atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteRequest request) {
        var cliente = this.clienteAssembler.toEntity(request);
        cliente.setClienteId(clienteId);
        return this.clienteAssembler.toModel(this.catalogoClienteService.salvar(cliente));
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long clienteId) {
        this.catalogoClienteService.excluir(clienteId);
    }

}
