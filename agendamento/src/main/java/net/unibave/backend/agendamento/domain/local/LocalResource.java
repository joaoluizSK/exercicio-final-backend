package net.unibave.backend.agendamento.domain.local;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "Cadastro de Locais", tags = "locais",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Path("/locais")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LocalResource {

    @ApiOperation(value = "Adicionar local")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Local adicionado com sucesso", response = Local.class)
    })
    @POST
    Response adicionar(@Valid final Local local);

    @ApiOperation(value = "Atualizar local")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Local atualizado com sucesso", response = Local.class)
            ,
            @ApiResponse(code = 404, message = "Local não encontrado")
    })
    @PUT
    @Path(value = "{id}")
    Response atualizar(@PathParam(value = "id") final Long id, @Valid final Local local);

    @ApiOperation(value = "Deletar local")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Local deletado com sucesso")
            ,
            @ApiResponse(code = 404, message = "Local não encontrado")
    })
    @DELETE
    @Path(value = "{id}")
    Response deletar(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Buscar local")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Local retornado com sucesso", response = Local.class)
            ,
            @ApiResponse(code = 404, message = "Local não encontrado")
    })
    @GET
    @Path(value = "{id}")
    Response buscar(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Listar locais")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de locais", response = Local.class, responseContainer = "List")
    })
    @GET
    Response listar(@QueryParam(value = "nome") final String nome,
                    @QueryParam(value = "page") @DefaultValue("0") final int page,
                    @QueryParam(value = "limit") @DefaultValue("10") final int limit,
                    @QueryParam(value = "sort") @DefaultValue("id") final String sort,
                    @QueryParam(value = "direction") @DefaultValue("asc") final String direction);


}
