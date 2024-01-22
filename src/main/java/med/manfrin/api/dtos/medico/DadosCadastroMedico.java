package med.manfrin.api.dtos.medico;

import med.manfrin.api.domain.medico.Especialidade;
import med.manfrin.api.dtos.endereco.DadosEndereco;

public record DadosCadastroMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco dadosEndereco
) {
}
