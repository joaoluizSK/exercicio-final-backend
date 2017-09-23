package net.unibave.backend.agendamento.domain.agendamento.participacao;

import java.util.Optional;

public interface ParticipacaoService {

    Participacao adicionar(Participacao novaParticipacao);

    Participacao atualizar(Long id, Participacao participacao);

    Optional<Participacao> buscar(Long id);

    void deletar(Long id);

}
