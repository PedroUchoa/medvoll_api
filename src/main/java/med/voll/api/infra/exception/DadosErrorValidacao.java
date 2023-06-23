package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

import java.util.function.Function;

public record DadosErrorValidacao(String campo, String mensagem){
    public DadosErrorValidacao(FieldError err){
        this(err.getField(),err.getDefaultMessage());
    }

}
