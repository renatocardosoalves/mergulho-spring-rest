package com.github.renatocardosoalves.algalog.api.assembler;

import com.github.renatocardosoalves.algalog.api.model.response.EntregaResponse;
import com.github.renatocardosoalves.algalog.api.model.request.EntregaRequest;
import com.github.renatocardosoalves.algalog.domain.model.Entrega;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EntregaAssembler {

    private final ModelMapper modelMapper;

    public EntregaResponse toModel(Entrega entrega) {
        return this.modelMapper.map(entrega, EntregaResponse.class);
    }

    public List<EntregaResponse> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .toList();
    }

    public Entrega toEntity(EntregaRequest entregaRequest) {
        return this.modelMapper.map(entregaRequest, Entrega.class);
    }

}
