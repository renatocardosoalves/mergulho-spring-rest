package com.github.renatocardosoalves.algalog.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(setterPrefix = "com")
@JsonInclude(Include.NON_NULL)
public class Problema {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

}
