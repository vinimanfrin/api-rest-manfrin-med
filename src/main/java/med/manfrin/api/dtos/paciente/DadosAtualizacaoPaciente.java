package med.manfrin.api.dtos.paciente;

import jakarta.validation.constraints.NotNull;
import med.manfrin.api.dtos.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
