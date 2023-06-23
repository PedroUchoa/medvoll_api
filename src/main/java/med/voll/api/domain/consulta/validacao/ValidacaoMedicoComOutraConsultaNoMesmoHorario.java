package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        boolean medicoPossuiOutraConsulta =consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsulta){
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }


}
