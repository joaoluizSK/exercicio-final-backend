package net.unibave.backend.agendamento.domain.agendamento.participacao;

import lombok.*;
import net.unibave.backend.agendamento.domain.pessoa.Pessoa;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Participacao {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Pessoa pessoa;

    @Column(name = "confirmado")
    private Boolean confirmado;

    @Column(name = "observacao", length = 100, nullable = false)
    private String observacao;
}
