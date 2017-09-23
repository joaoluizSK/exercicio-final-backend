package net.unibave.backend.agendamento.domain.agendamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgendamentoService {

    Agendamento adicionar(Agendamento agendamento);

    Agendamento atualizar(Long id, Agendamento agendamento);

    Optional<Agendamento> buscar(Long id);

    void deletar(Long id);

    Page<Agendamento> listar(Pageable pageable);

    void confirmarParticipacao(Long id, Long idParticipacao);
}
