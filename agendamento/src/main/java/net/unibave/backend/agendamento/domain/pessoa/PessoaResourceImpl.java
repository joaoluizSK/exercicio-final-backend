package net.unibave.backend.agendamento.domain.pessoa;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class PessoaResourceImpl implements PessoaResource {

    @Inject
    private PessoaService pessoaService;

    @Override
    public Response adicionar(Pessoa pessoa) {
        return Response.ok(pessoaService.adicionar(pessoa)).build();
    }

    @Override
    public Response atualizar(Long id, Pessoa pessoa) {
        return Response.ok(pessoaService.atualizar(id, pessoa)).build();
    }

    @Override
    public Response deletar(Long id) {
        pessoaService.deletar(id);
        return Response.noContent().build();
    }

    @Override
    public Response buscar(Long id) {
        Pessoa pessoa = pessoaService.buscar(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new PessoaDTO(pessoa)).build();
    }

    @Override
    public Response listar(String nome, int page, int limit, String sort, String direction) {
        Pageable pageagle = new PageRequest(page, limit,
                Sort.Direction.fromString(direction), sort);
        return Response.ok(pessoaService.listar(pageagle, nome)).build();
    }
}
