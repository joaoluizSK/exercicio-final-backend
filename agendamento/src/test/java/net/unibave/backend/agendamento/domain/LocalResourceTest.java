package net.unibave.backend.agendamento.domain;

import net.unibave.backend.agendamento.PageDTO;
import net.unibave.backend.agendamento.domain.local.Local;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LocalResourceTest {

    @Inject
    private TestRestTemplate restTemplate;

    private static final String BASE_URI = "/api/locais";

    @Test
    public void testCadastrarLocal() throws Exception {

        final Local local = Local
                .builder()
                .nome("CEGERO")
                .capacidade(100)
                .build();
        ResponseEntity<Local> response = restTemplate
                .postForEntity(BASE_URI, local, Local.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Local localCriado = response.getBody();
        assertThat(localCriado.getId()).isNotNull();
        assertThat(localCriado.getNome()).isEqualTo(local.getNome());
        assertThat(localCriado.getCapacidade()).isEqualTo(local.getCapacidade());

        restTemplate
                .exchange(BASE_URI + "/" + localCriado.getId(), HttpMethod.DELETE, null, String.class);

    }

    @Test
    public void testAlterarLocal() {
        final Local local = Local
                .builder()
                .nome("Vila Maria")
                .capacidade(50)
                .build();
        ResponseEntity<Local> response = restTemplate
                .postForEntity(BASE_URI, local, Local.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Local localCriado = response.getBody();
        assertThat(localCriado.getId()).isNotNull();
        assertThat(localCriado.getNome()).isEqualTo(local.getNome());
        assertThat(localCriado.getCapacidade()).isEqualTo(local.getCapacidade());

        localCriado.setNome("Parque dos Principes");

        restTemplate.put(BASE_URI + "/" + localCriado.getId(), localCriado, Local.class);

        ResponseEntity<Local> responseConsulta = restTemplate
                .exchange(BASE_URI + "/" + localCriado.getId(), HttpMethod.GET, null, Local.class);

        Local localConsulta = responseConsulta.getBody();

        assertThat(localConsulta.getNome()).isEqualTo(localCriado.getNome());
        restTemplate
                .exchange(BASE_URI + "/" + localCriado.getId(), HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void testBuscarLocal() {

        final Local local = Local
                .builder()
                .nome("Vila Maria")
                .capacidade(50)
                .build();
        ResponseEntity<Local> response = restTemplate
                .postForEntity(BASE_URI, local, Local.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Local localCriado = response.getBody();

        ResponseEntity<Local> responseConsulta = restTemplate
                .exchange(BASE_URI + "/" + localCriado.getId(), HttpMethod.GET, null, Local.class);
        assertThat(responseConsulta.getStatusCode()).isEqualTo(HttpStatus.OK);
        Local localBusca = response.getBody();
        assertThat(localBusca.getId()).isNotNull();
        restTemplate
                .exchange(BASE_URI + "/" + localCriado.getId(), HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void testListarLocal() {
        final Local local = Local
                .builder()
                .nome("Diox")
                .capacidade(100)
                .build();
        ResponseEntity<Local> response = restTemplate.postForEntity(BASE_URI, local, Local.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<PageDTO<Local>> responseConsulta = restTemplate.exchange(BASE_URI, HttpMethod.GET, null, getPageTypeReference());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PageDTO<Local> localConsulta = responseConsulta.getBody();
        assertThat(localConsulta.getTotalElements()).isEqualTo(1);
        assertThat(localConsulta.getContent().size()).isEqualTo(1);
    }

    @Test
    public void testDeletarLocal() {

        final Local local = Local
                .builder()
                .nome("Diox")
                .capacidade(100)
                .build();
        ResponseEntity<Local> response = restTemplate.postForEntity(BASE_URI, local, Local.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Local localCriado = response.getBody();

        ResponseEntity<String> responseDelete = restTemplate
                .exchange(BASE_URI + "/" + localCriado.getId(), HttpMethod.DELETE, null, String.class);
        assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Local> responseConsultaDelete = restTemplate
                .exchange(BASE_URI + "/"
                                + localCriado.getId(),
                        HttpMethod.GET, null, Local.class);
        assertThat(responseConsultaDelete.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

    }

    private ParameterizedTypeReference<PageDTO<Local>>
    getPageTypeReference() {
        return new ParameterizedTypeReference<PageDTO<Local>>() {
        };
    }

}
