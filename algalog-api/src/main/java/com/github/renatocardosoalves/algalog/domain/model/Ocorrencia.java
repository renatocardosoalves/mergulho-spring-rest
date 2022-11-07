package com.github.renatocardosoalves.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ocorrencia {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ocorrenciaId;

    private String descricao;

    private OffsetDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;

}
