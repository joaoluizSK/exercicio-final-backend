package net.unibave.backend.agendamento.domain.local;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Local {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "capacidade", length = 10, nullable = false)
    private Integer capacidade;

}
