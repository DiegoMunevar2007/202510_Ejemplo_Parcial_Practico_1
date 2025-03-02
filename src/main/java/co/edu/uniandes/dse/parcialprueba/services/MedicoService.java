package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoEntity createMedicos(MedicoEntity medico) throws IllegalOperationException{
        log.info("Inicío la creación del medico con ID"+medico.getId());
        if (!medico.getRegistroMedico().startsWith("RM")){
            throw new IllegalOperationException("El registro médico debe empezar con RM");
        }
        log.info("Terminó al creación del médico con ID "+medico.getId());
        return medicoRepository.save(medico);
    }
}
