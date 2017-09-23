package net.unibave.backend.agendamento.domain.agendamento.participacao;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class ParticipacaoServiceImpl implements ParticipacaoService {

    @Inject
    private ParticipacaoRepository participacaoRepository;

    @Override
    public Participacao adicionar(Participacao novaParticipacao) {
        return participacaoRepository.save(novaParticipacao);
    }

    @Override
    public Participacao atualizar(Long id, Participacao participacao) {
        if (!id.equals(participacao.getId())) {
            throw new IllegalArgumentException("Id inválido");
        }
        return participacaoRepository.save(participacao);
    }

    @Override
    public Optional<Participacao> buscar(Long id) {
        return Optional.ofNullable(participacaoRepository.findOne(id));
    }

    @Override
    public void deletar(Long id) {
        buscar(id).ifPresent(participacaoRepository::delete);
    }
}
