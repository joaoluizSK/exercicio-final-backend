package net.unibave.backend.agendamento.domain.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PessoaService {

    Pessoa adicionar(Pessoa novaPessoa);

    Pessoa atualizar(Long id, Pessoa pessoa);

    Optional<Pessoa> buscar(Long id);

    void deletar(Long id);

    Page<Pessoa> listar(Pageable pageable, String nome);

}

