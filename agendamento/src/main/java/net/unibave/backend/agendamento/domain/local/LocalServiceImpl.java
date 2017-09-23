package net.unibave.backend.agendamento.domain.local;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService {

    @Inject
    private LocalRepository locaRepository;

    @Override
    public Local adicionar(Local novoLocal) {
        return locaRepository.save(novoLocal);
    }

    @Override
    public Local atualizar(Long id, Local local) {
        if (!id.equals(local.getId())) {
            throw new IllegalArgumentException("Id inválido");
        }
        return locaRepository.save(local);
    }

    @Override
    public Optional<Local> buscar(Long id) {
        return Optional.ofNullable(locaRepository.findOne(id));
    }

    @Override
    public void deletar(final Long id) {
        buscar(id).ifPresent(locaRepository::delete);
    }

    @Override
    public Page<Local> listar(Pageable pageable, String nome) {
        return nome != null
                ? locaRepository.findByNomeContaining(pageable, nome)
                : locaRepository.findAll(pageable);
    }
}
