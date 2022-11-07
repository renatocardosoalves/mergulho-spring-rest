package com.github.renatocardosoalves.algalog.domain.exception;

public abstract class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem, Long recursoId) {
        super(String.format(mensagem, recursoId));
    }

}
