package med.manfrin.api.dtos.medico;

import med.manfrin.api.domain.endereco.Endereco;
import med.manfrin.api.domain.medico.Especialidade;
import med.manfrin.api.domain.medico.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String telefone,String crm, Especialidade especialidade, Endereco endereco, Boolean ativo) {

    public DadosDetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(),medico.getEndereco(), medico.getAtivo());
    }
}
