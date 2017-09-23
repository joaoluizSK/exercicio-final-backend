package net.unibave.backend.agendamento.domain.pessoa;

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
public class Pessoa {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "telefone")
    private String telefone;
}
