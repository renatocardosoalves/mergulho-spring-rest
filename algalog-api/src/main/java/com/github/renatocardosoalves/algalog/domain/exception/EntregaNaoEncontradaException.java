package com.github.renatocardosoalves.algalog.domain.exception;

public class EntregaNaoEncontradaException extends RecursoNaoEncontradoException {

    public EntregaNaoEncontradaException(Long clienteId) {
        super("A entrega com id %d não foi encontrada.", clienteId);
    }

}
