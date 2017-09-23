package net.unibave.backend.agendamento.domain.local;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@Component
public class LocalResourceImpl implements LocalResource {

    @Inject
    private LocalService localService;

    @Override
    public Response adicionar(Local local) {
        return Response.ok(localService.adicionar(local)).build();
    }

    @Override
    public Response atualizar(Long id, Local local) {
        return Response.ok(localService.atualizar(id, local)).build();
    }

    @Override
    public Response deletar(Long id) {
        localService.deletar(id);
        return Response.noContent().build();
    }

    @Override
    public Response buscar(Long id) {
        Local local = localService.buscar(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new LocalDTO(local)).build();
    }

    @Override
    public Response listar(String nome, int page, int limit, String sort, String direction) {
        Pageable pageagle = new PageRequest(page, limit,
                Sort.Direction.fromString(direction), sort);
        return Response.ok(localService.listar(pageagle, nome)).build();
    }
}
