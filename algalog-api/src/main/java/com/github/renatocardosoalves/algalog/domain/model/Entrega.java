package com.github.renatocardosoalves.algalog.domain.model;

import com.github.renatocardosoalves.algalog.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entregaId;

    private BigDecimal taxa;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @Embedded
    private Destinatario destinatario;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    public Ocorrencia adicionarOcorrencia(String descricao) {
        var ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        this.ocorrencias.add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if (StatusEntrega.PENDENTE != this.status) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }
        this.status = StatusEntrega.FINALIZADA;
        this.dataFinalizacao = OffsetDateTime.now();
    }

}
