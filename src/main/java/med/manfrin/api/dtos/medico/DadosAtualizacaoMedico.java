package med.manfrin.api.dtos.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.manfrin.api.dtos.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
