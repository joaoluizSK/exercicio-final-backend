package net.unibave.backend.agendamento.domain.agendamento;

import lombok.Data;
import net.unibave.backend.agendamento.domain.local.Local;

import java.util.Date;

@Data
public class AgendamentoDTO {

    private Long id;
    private Date dataInicio;
    private Date dataFim;
    private String observacao;
    private Local local;

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.dataInicio = agendamento.getHoraInicio();
        this.dataFim = agendamento.getHoraFim();
        this.observacao = agendamento.getObservacoes();
        this.local = agendamento.getLocal();
    }
}
