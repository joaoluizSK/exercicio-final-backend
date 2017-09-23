package net.unibave.backend.agendamento.domain.local;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LocalService {
    Local adicionar(Local novoLocal);

    Local atualizar(Long id, Local local);

    Optional<Local> buscar(Long id);

    void deletar(Long id);

    Page<Local> listar(Pageable pageable, String nome);

}
