package net.unibave.backend.agendamento.domain.pessoa;

import lombok.Data;

@Data
public class PessoaDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.telefone = pessoa.getTelefone();
    }
}
