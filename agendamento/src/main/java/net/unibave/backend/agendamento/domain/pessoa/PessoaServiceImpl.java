package net.unibave.backend.agendamento.domain.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class PessoaServiceImpl  implements PessoaService {

    @Inject
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa adicionar(Pessoa novaPessoa) {
        return pessoaRepository.save(novaPessoa);
    }
    @Override
    public Pessoa atualizar(Long id, Pessoa pessoa) {
        if (!id.equals(pessoa.getId())) {
            throw new IllegalArgumentException("Id inválido");
        }
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> buscar(Long id) {
        return Optional.ofNullable(pessoaRepository.findOne(id));
    }

    @Override
    public void deletar(final Long id) {
        buscar(id).ifPresent(pessoaRepository::delete);
    }

    @Override
    public Page<Pessoa> listar(Pageable pageable, String nome) {
        return nome != null
                ? pessoaRepository.findByNomeContaining(pageable, nome)
                : pessoaRepository.findAll(pageable);
    }

}
