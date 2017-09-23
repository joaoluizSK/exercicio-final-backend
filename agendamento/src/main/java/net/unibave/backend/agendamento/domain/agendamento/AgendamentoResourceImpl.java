package net.unibave.backend.agendamento.domain.agendamento;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@Component
public class AgendamentoResourceImpl implements AgendamentoResource {

    @Inject
    private AgendamentoService agendamentoService;

    @Override
    public Response adicionar(Agendamento agendamento) {
        return Response.ok(agendamentoService.adicionar(agendamento)).build();
    }

    @Override
    public Response atualizar(Long id, Agendamento agendamento) {
        return Response.ok(agendamentoService.atualizar(id, agendamento)).build();
    }

    @Override
    public Response deletar(Long id) {
        agendamentoService.deletar(id);
        return Response.noContent().build();
    }

    @Override
    public Response buscar(Long id) {
        Agendamento agendamento = agendamentoService.buscar(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new AgendamentoDTO(agendamento)).build();
    }

    @Override
    public Response listar(int page, int limit, String sort, String direction) {
        Pageable pageagle = new PageRequest(page, limit,
                Sort.Direction.fromString(direction), sort);
        return Response.ok(agendamentoService.listar(pageagle)).build();
    }

    @Override
    public Response listarParticipacoes(Long id) {
        Agendamento agendamento = agendamentoService.buscar(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(agendamento.getParticipacoes()).build();
    }

    @Override
    public Response confirmarParticipacao(Long id, Long idParticipacao) {
        agendamentoService.confirmarParticipacao(id, idParticipacao);
        return Response.ok().build();
    }
}
