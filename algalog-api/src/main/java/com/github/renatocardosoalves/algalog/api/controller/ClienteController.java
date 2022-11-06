package com.github.renatocardosoalves.algalog.api.controller;

import com.github.renatocardosoalves.algalog.domain.model.Cliente;
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

    @GetMapping
    public List<Cliente> listar() {
        return this.catalogoClienteService.listar();
    }

    @GetMapping("/{clienteId}")
    public Cliente buscar(@PathVariable Long clienteId) {
        return this.catalogoClienteService.buscar(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody @Valid Cliente cliente) {
        return this.catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public Cliente atualizar(@PathVariable Long clienteId, @RequestBody @Valid Cliente cliente) {
        cliente.setClienteId(clienteId);
        return this.catalogoClienteService.salvar(cliente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long clienteId) {
        this.catalogoClienteService.excluir(clienteId);
    }

}
