package net.unibave.backend.agendamento.domain;

import net.unibave.backend.agendamento.domain.agendamento.Agendamento;
import net.unibave.backend.agendamento.domain.agendamento.participacao.Participacao;
import net.unibave.backend.agendamento.domain.local.Local;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AgendamentoResourceTest {

    @Inject
    private TestRestTemplate restTemplate;

    private static final String BASE_URI = "/api/agendamentos";

    @Test
    public void testCadastrarAgendamento() throws Exception {
        final Agendamento agendamento = Agendamento
                .builder()
                .data(new Date())
                .horaInicio(new Date())
                .horaFim(new Date())
                .local(Local.builder().nome("SER").capacidade(100).build())
                .participacoes(Lists.newArrayList(Participacao.builder().confirmado(false).observacao("ola").build()))
                .build();
        ResponseEntity<Agendamento> response = restTemplate
                .postForEntity(BASE_URI, agendamento, Agendamento.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Agendamento agendamentoCriado = response.getBody();
        assertThat(agendamentoCriado.getId()).isNotNull();

        restTemplate.exchange(BASE_URI + "/" + agendamentoCriado.getId(), HttpMethod.DELETE, null, String.class);

    }

    @Test
    public void testBuscarAgendamento() {
        final Agendamento agendamento = Agendamento
                .builder()
                .data(new Date())
                .horaInicio(new Date())
                .horaFim(new Date())
                .local(Local.builder().nome("SER").capacidade(100).build())
                .participacoes(Lists.newArrayList(Participacao.builder().confirmado(false).observacao("ola").build()))
                .build();
        ResponseEntity<Agendamento> response = restTemplate
                .postForEntity(BASE_URI, agendamento, Agendamento.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Agendamento agendamentoCriado = response.getBody();
        assertThat(agendamentoCriado.getId()).isNotNull();

        ResponseEntity<Local> responseConsulta = restTemplate
                .exchange(BASE_URI + "/" + agendamentoCriado.getId(), HttpMethod.GET, null, Local.class);
        assertThat(responseConsulta.getStatusCode()).isEqualTo(HttpStatus.OK);
        Agendamento agendamentoBusca = response.getBody();
        assertThat(agendamentoBusca.getId()).isNotNull();

        restTemplate
                .exchange(BASE_URI + "/" + agendamentoCriado.getId(), HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void testAlterarAgendamento() {
        final Agendamento agendamento = Agendamento
                .builder()
                .data(new Date())
                .horaInicio(new Date())
                .horaFim(new Date())
                .local(Local.builder().nome("SER").capacidade(100).build())
                .participacoes(Lists.newArrayList(Participacao.builder().confirmado(false).observacao("ola").build()))
                .build();
        ResponseEntity<Agendamento> response = restTemplate
                .postForEntity(BASE_URI, agendamento, Agendamento.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Agendamento agendamentoCriado = response.getBody();
        assertThat(agendamentoCriado.getId()).isNotNull();

        agendamentoCriado.setLocal(Local.builder().nome("UNISUL").capacidade(100).build());

        restTemplate.put(BASE_URI + "/" + agendamentoCriado.getId(), agendamentoCriado, Local.class);

        ResponseEntity<Agendamento> responseConsulta = restTemplate
                .exchange(BASE_URI + "/" + agendamentoCriado.getId(), HttpMethod.GET, null, Agendamento.class);

        Agendamento agendamentoConsulta = responseConsulta.getBody();

        assertThat(agendamentoConsulta.getId()).isEqualTo(agendamentoCriado.getId());
        restTemplate
                .exchange(BASE_URI + "/" + agendamentoCriado.getId(), HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void testDeletarAgendamento() {

        final Agendamento agendamento = Agendamento
                .builder()
                .data(new Date())
                .horaInicio(new Date())
                .horaFim(new Date())
                .local(Local.builder().nome("SER").capacidade(100).build())
                .participacoes(Lists.newArrayList(Participacao.builder().confirmado(false).observacao("ola").build()))
                .build();
        ResponseEntity<Agendamento> response = restTemplate
                .postForEntity(BASE_URI, agendamento, Agendamento.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Agendamento agendamentoCriado = response.getBody();
        assertThat(agendamentoCriado.getId()).isNotNull();

        ResponseEntity<String> responseDelete = restTemplate
                .exchange(BASE_URI + "/" + agendamentoCriado.getId(), HttpMethod.DELETE, null, String.class);
        assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Agendamento> responseConsultaDelete = restTemplate
                .exchange(BASE_URI + "/"
                                + agendamentoCriado.getId(),
                        HttpMethod.GET, null, Agendamento.class);
        assertThat(responseConsultaDelete.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

    }

}
