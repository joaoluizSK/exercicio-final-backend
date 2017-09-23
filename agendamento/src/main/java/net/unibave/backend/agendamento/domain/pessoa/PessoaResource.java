package net.unibave.backend.agendamento.domain.pessoa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Cadastro de Pessoas", tags = "pessoas",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Path("/pessoas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public interface PessoaResource {

    @ApiOperation(value = "Adicionar Pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pessoa adicionada com sucesso", response = Pessoa.class)
    })
    @POST
    Response adicionar(@Valid final Pessoa pessoa);

    @ApiOperation(value = "Atualizar Pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pessoa atualizada com sucesso", response = Pessoa.class)
            ,
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    @PUT
    @Path(value = "{id}")
    Response atualizar(@PathParam(value = "id") final Long id, @Valid final Pessoa pessoa);

    @ApiOperation(value = "Deletar Pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pessoa deletada com sucesso")
            ,
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    @DELETE
    @Path(value = "{id}")
    Response deletar(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Buscar Pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pessoa retornada com sucesso", response = Pessoa.class)
            ,
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    @GET
    @Path(value = "{id}")
    Response buscar(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Listar Pessoas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de Pessoas", response = Pessoa.class, responseContainer = "List")
    })
    @GET
    Response listar(@QueryParam(value = "nome") final String nome,
                    @QueryParam(value = "page") @DefaultValue("0") final int page,
                    @QueryParam(value = "limit") @DefaultValue("10") final int limit,
                    @QueryParam(value = "sort") @DefaultValue("id") final String sort,
                    @QueryParam(value = "direction") @DefaultValue("asc") final String direction);


}

