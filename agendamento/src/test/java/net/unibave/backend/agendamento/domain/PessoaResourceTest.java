package net.unibave.backend.agendamento.domain;

import net.unibave.backend.agendamento.PageDTO;
import net.unibave.backend.agendamento.domain.pessoa.Pessoa;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PessoaResourceTest {

    @Inject
    private TestRestTemplate restTemplate;

    private static final String BASE_URI = "/api/pessoas";

    @Test
    public void testCadastrarPessoa() throws Exception {

        final Pessoa pessoa = Pessoa
                .builder()
                .nome("Eduardo")
                .email("heinzem.eduardo@gmail.com")
                .telefone("996047902")
                .build();
        ResponseEntity<Pessoa> response = restTemplate
                .postForEntity(BASE_URI, pessoa, Pessoa.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Pessoa pessoaCriada = response.getBody();
        assertThat(pessoaCriada.getId()).isNotNull();
        assertThat(pessoaCriada.getNome()).isEqualTo(pessoa.getNome());
        assertThat(pessoaCriada.getEmail()).isEqualTo(pessoa.getEmail());
        assertThat(pessoaCriada.getTelefone()).isEqualTo(pessoa.getTelefone());

        restTemplate
                .exchange(BASE_URI + "/" + pessoaCriada.getId(), HttpMethod.DELETE, null, String.class);

    }

    @Test
    public void testAlterarPessoa() {
        final Pessoa pessoa = Pessoa
                .builder()
                .nome("Eduardo Heinzem")
                .email("eduardo@projedata.com.br")
                .build();
        ResponseEntity<Pessoa> response = restTemplate
                .postForEntity(BASE_URI, pessoa, Pessoa.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Pessoa pessoaCriada = response.getBody();
        assertThat(pessoaCriada.getId()).isNotNull();
        assertThat(pessoaCriada.getNome()).isEqualTo(pessoa.getNome());
        assertThat(pessoaCriada.getEmail()).isEqualTo(pessoa.getEmail());

        pessoaCriada.setTelefone("996047902");

        restTemplate.put(BASE_URI + "/" + pessoaCriada.getId(), pessoaCriada, Pessoa.class);

        ResponseEntity<Pessoa> responseConsulta = restTemplate
                .exchange(BASE_URI + "/" + pessoaCriada.getId(), HttpMethod.GET, null, Pessoa.class);

        Pessoa pessoaConsulta = responseConsulta.getBody();

        assertThat(pessoaConsulta.getTelefone()).isEqualTo(pessoaCriada.getTelefone());
        restTemplate
                .exchange(BASE_URI + "/" + pessoaCriada.getId(), HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void testBuscarPessoa() {

        final Pessoa pessoa = Pessoa
                .builder()
                .nome("Eduardo")
                .email("eduardo@teste.com.br")
                .telefone("9999999999")
                .build();
        ResponseEntity<Pessoa> response = restTemplate
                .postForEntity(BASE_URI, pessoa, Pessoa.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Pessoa pessoaCriada = response.getBody();

        ResponseEntity<Pessoa> responseConsulta = restTemplate
                .exchange(BASE_URI + "/" + pessoaCriada.getId(), HttpMethod.GET, null, Pessoa.class);
        assertThat(responseConsulta.getStatusCode()).isEqualTo(HttpStatus.OK);
        Pessoa pessoaBusca = response.getBody();
        assertThat(pessoaBusca.getId()).isNotNull();
        restTemplate
                .exchange(BASE_URI + "/" + pessoaCriada.getId(), HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void testListarPessoa() {
        final Pessoa pessoa = Pessoa
                .builder()
                .nome("Eduardo")
                .email("eduardo@teste.com.br")
                .telefone("9999999999")
                .build();
        ResponseEntity<Pessoa> response = restTemplate.postForEntity(BASE_URI, pessoa, Pessoa.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<PageDTO<Pessoa>> responseConsulta = restTemplate.exchange(BASE_URI, HttpMethod.GET, null, getPageTypeReference());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PageDTO<Pessoa> pessoaConsulta = responseConsulta.getBody();
        assertThat(pessoaConsulta.getTotalElements()).isEqualTo(1);
        assertThat(pessoaConsulta.getContent().size()).isEqualTo(1);
    }

    @Test
    public void testDeletarPessoa() {

        final Pessoa pessoa = Pessoa
                .builder()
                .nome("Eduardo")
                .email("eduardo@teste.com.br")
                .telefone("9999999999")
                .build();
        ResponseEntity<Pessoa> response = restTemplate.postForEntity(BASE_URI, pessoa, Pessoa.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Pessoa pessoa1Criada = response.getBody();

        ResponseEntity<String> responseDelete = restTemplate
                .exchange(BASE_URI + "/" + pessoa1Criada.getId(), HttpMethod.DELETE, null, String.class);
        assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Pessoa> responseConsultaDelete = restTemplate
                .exchange(BASE_URI + "/"
                                + pessoa1Criada.getId(),
                        HttpMethod.GET, null, Pessoa.class);
        assertThat(responseConsultaDelete.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

    }

    private ParameterizedTypeReference<PageDTO<Pessoa>>
    getPageTypeReference() {
        return new ParameterizedTypeReference<PageDTO<Pessoa>>() {
        };
    }

}