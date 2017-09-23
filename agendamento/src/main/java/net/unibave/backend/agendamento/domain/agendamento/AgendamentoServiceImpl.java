package net.unibave.backend.agendamento.domain.agendamento;

import net.unibave.backend.agendamento.domain.agendamento.participacao.ParticipacaoRepository;
import net.unibave.backend.agendamento.domain.agendamento.participacao.ParticipacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    @Inject
    private AgendamentoRepository agendamentoRepository;

    @Inject
    private ParticipacaoService participacaoService;

    @Override
    public Agendamento adicionar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    @Override
    public Agendamento atualizar(Long id, Agendamento agendamento) {
        if (!id.equals(agendamento.getId())) {
            throw new IllegalArgumentException("Id inválido");
        }
        return agendamentoRepository.save(agendamento);
    }

    @Override
    public Optional<Agendamento> buscar(Long id) {
        return Optional.ofNullable(agendamentoRepository.findOne(id));
    }

    @Override
    public void deletar(Long id) {
        buscar(id).ifPresent(agendamentoRepository::delete);
    }

    @Override
    public Page<Agendamento> listar(Pageable pageable) {
        return agendamentoRepository.findAll(pageable);
    }

    @Override
    public void confirmarParticipacao(Long id, Long idParticipacao) {
        Optional<Agendamento> agendamento = buscar(id);

        if (agendamento.isPresent()) {
            agendamento.get().getParticipacoes().forEach(participacao -> {
                if (idParticipacao == participacao.getId()) {
                    participacao.setConfirmado(Boolean.TRUE);
                    participacaoService.atualizar(participacao.getId(), participacao);
                }
            });

        }

    }
}
