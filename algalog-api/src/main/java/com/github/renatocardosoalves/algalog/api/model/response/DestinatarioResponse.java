package com.github.renatocardosoalves.algalog.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioResponse {

    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

}
