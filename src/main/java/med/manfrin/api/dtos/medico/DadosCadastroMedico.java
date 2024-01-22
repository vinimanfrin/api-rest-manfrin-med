package med.manfrin.api.dtos.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.manfrin.api.domain.medico.Especialidade;
import med.manfrin.api.dtos.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank
        String nome,

        @NotBlank @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank @Pattern(regexp = "\\d{4,6}") //expressão regular , são de 4 a 6 digitos
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull @Valid
        DadosEndereco dadosEndereco
) {
}
