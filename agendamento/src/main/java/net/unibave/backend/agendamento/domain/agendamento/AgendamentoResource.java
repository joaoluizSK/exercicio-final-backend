package net.unibave.backend.agendamento.domain.agendamento;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.unibave.backend.agendamento.domain.agendamento.participacao.Participacao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Cadastro de Agendamentos", tags = "agendamentos",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Path("/agendamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AgendamentoResource {
    @ApiOperation(value = "Adicionar agendamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento adicionado com sucesso", response = Agendamento.class)
    })
    @POST
    Response adicionar(@Valid final Agendamento agendamento);

    @ApiOperation(value = "Atualizar agendamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento atualizado com sucesso", response = Agendamento.class)
            ,
            @ApiResponse(code = 404, message = "Agendamento não encontrado")
    })
    @PUT
    @Path(value = "{id}")
    Response atualizar(@PathParam(value = "id") final Long id, @Valid final Agendamento agendamento);

    @ApiOperation(value = "Deletar agendamento")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Agendamento deletado com sucesso")
            ,
            @ApiResponse(code = 404, message = "Agendamento não encontrado")
    })
    @DELETE
    @Path(value = "{id}")
    Response deletar(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Buscar agendamneto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Agendamento retornado com sucesso", response = Agendamento.class)
            ,
            @ApiResponse(code = 404, message = "Agendamento não encontrado")
    })
    @GET
    @Path(value = "{id}")
    Response buscar(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Listar agendamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de agendamentos", response = Agendamento.class, responseContainer = "List")
    })
    @GET
    Response listar(@QueryParam(value = "page") @DefaultValue("0") final int page,
                    @QueryParam(value = "limit") @DefaultValue("10") final int limit,
                    @QueryParam(value = "sort") @DefaultValue("id") final String sort,
                    @QueryParam(value = "direction") @DefaultValue("asc") final String direction);


    @ApiOperation(value = "Listar participantes do agendamento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de participantes do agendamento", response = Participacao.class, responseContainer = "List")
    })
    @GET
    @Path(value = "{id}/participacoes")
    Response listarParticipacoes(@PathParam(value = "id") final Long id);


    @ApiOperation(value = "Confirmar participacao")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Participacao confirmada", response = Participacao.class)
            ,
            @ApiResponse(code = 404, message = "Participação não encontrada")
    })
    @POST
    @Path(value = "{id}/participacoes/{idparticipacao}")
    Response confirmarParticipacao(@PathParam(value = "id") final Long id, @PathParam(value = "idparticipacao") final Long idParticipacao);

}
