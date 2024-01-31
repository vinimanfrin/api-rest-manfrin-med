package med.manfrin.api.infra.exception;

public class ValidacaoAgendamentoException extends RuntimeException {
    public ValidacaoAgendamentoException(String mensagem) {
        super(mensagem);
    }
}
