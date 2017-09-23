package net.unibave.backend.agendamento.domain.agendamento;

import lombok.*;
import net.unibave.backend.agendamento.domain.agendamento.participacao.Participacao;
import net.unibave.backend.agendamento.domain.local.Local;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Agendamento {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(value = TemporalType.DATE)
    private Date data;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date horaInicio;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date horaFim;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participacao> participacoes;

    @OneToOne
    private Local local;

    @Column(name = "observacoes", length = 100)
    private String observacoes;

}