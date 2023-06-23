package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class ValidacaoNumeroConsultasPaciente implements ValidadorAgendamentoConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados){
        LocalDateTime primeiroHorario = dados.data().withHour(7);
        LocalDateTime ultimoHorario = dados.data().withHour(18);
        Boolean pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui consulta agendada nesse dia");
        }
    }


}
