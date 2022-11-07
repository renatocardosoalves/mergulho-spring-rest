package com.github.renatocardosoalves.algalog.api.assembler;

import com.github.renatocardosoalves.algalog.api.model.request.ClienteRequest;
import com.github.renatocardosoalves.algalog.api.model.response.ClienteResponse;
import com.github.renatocardosoalves.algalog.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ClienteAssembler {

    private final ModelMapper modelMapper;

    public ClienteResponse toModel(Cliente cliente) {
        return this.modelMapper.map(cliente, ClienteResponse.class);
    }

    public List<ClienteResponse> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .toList();
    }

    public Cliente toEntity(ClienteRequest clienteRequest) {
        return this.modelMapper.map(clienteRequest, Cliente.class);
    }

}
