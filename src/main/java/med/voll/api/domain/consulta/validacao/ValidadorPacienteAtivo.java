package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        Boolean pacienteAtivo = pacienteRepository.getReferenceById(dados.idPaciente()).isAtivo();
        if (!pacienteAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente que não existe");
        }
    }

}
