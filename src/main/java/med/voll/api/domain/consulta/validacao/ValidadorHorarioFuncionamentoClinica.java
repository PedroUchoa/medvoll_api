package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime dataConsulta = dados.data();
        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDoHorario = dataConsulta.getHour() < 7;
        boolean depoisDoEncerramento = dataConsulta.getHour() > 18;

        if (domingo || antesDoHorario || depoisDoEncerramento){
            throw new ValidacaoException("Consulta fora do horario de funcionamento");
        }
    }



}
