package med.voll.api.domain.consulta.validacao;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoAtivo implements ValidadorAgendamentoConsulta{
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }

        if(!medicoRepository.getReferenceById(dados.idMedico()).isAtivo()){
            throw new ValidacaoException("Esse medico não está ativo para consultas no momento");
        }

    }

}
