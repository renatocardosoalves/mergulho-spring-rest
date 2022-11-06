package com.github.renatocardosoalves.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

    private Long clienteId;
    private String nome;
    private String email;
    private String telefone;

}
