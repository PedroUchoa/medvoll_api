package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataConsulta = dados.data();
        LocalDateTime  dataAgora = LocalDateTime.now();

        if(Duration.between(dataAgora,dataConsulta).toMinutes() < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia de 30 minutos");
        }

    }


}
