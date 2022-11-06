package com.github.renatocardosoalves.algalog.controller;

import com.github.renatocardosoalves.algalog.domain.model.Cliente;
import com.github.renatocardosoalves.algalog.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        return this.clienteRepository.findAll();
    }

}
