package com.github.renatocardosoalves.algalog.api.assembler;

import com.github.renatocardosoalves.algalog.api.model.response.OcorrenciaResponse;
import com.github.renatocardosoalves.algalog.domain.model.Ocorrencia;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OcorrenciaAssembler {

    private final ModelMapper modelMapper;

    public OcorrenciaResponse toModel(Ocorrencia ocorrencia) {
        return this.modelMapper.map(ocorrencia, OcorrenciaResponse.class);
    }

    public List<OcorrenciaResponse> toCollectionModel(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toModel)
                .toList();
    }

}
