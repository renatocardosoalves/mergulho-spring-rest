package com.github.renatocardosoalves.algalog.controller;

import com.github.renatocardosoalves.algalog.domain.model.Cliente;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var faker = new Faker();

        var cliente1 = new Cliente();
        cliente1.setClienteId(1L);
        cliente1.setNome(faker.name().fullName());
        cliente1.setTelefone(faker.phoneNumber().phoneNumber());
        cliente1.setEmail(faker.internet().emailAddress());

        var cliente2 = new Cliente();
        cliente2.setClienteId(2L);
        cliente2.setNome(faker.name().fullName());
        cliente2.setTelefone(faker.phoneNumber().phoneNumber());
        cliente2.setEmail(faker.internet().emailAddress());

        return List.of(cliente1, cliente2);
    }

}
