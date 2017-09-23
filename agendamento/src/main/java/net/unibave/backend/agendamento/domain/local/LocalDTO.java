package net.unibave.backend.agendamento.domain.local;

import lombok.Data;

@Data
public class LocalDTO {

    private Long id;
    private String nome;
    private Integer capacidade;

    public LocalDTO(Local local) {
        this.id = local.getId();
        this.nome = local.getNome();
        this.capacidade = local.getCapacidade();
    }
}
