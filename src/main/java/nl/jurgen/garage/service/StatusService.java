package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.Carinspection;
import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.model.Status;
import nl.jurgen.garage.repository.CarinspectionRepository;
import nl.jurgen.garage.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired CarinspectionRepository carinspectionRepository;

    public EStatus getStatusOfCarinspection(long carinspectionid){
        if(carinspectionRepository.existsById(carinspectionid)) {
            Carinspection carinspection = carinspectionRepository.findById(carinspectionid).orElse(null);

            Status status = carinspection.getStatus();
            carinspection.setStatus(status);
        }
        return null;
    }

    public void changeStatus(long carinspectionid, EStatus eStatus) {

        if(carinspectionRepository.existsById(carinspectionid)){
            Carinspection carinspection = carinspectionRepository.findById(carinspectionid).orElse(null);

            Status status = statusRepository.findByName(eStatus);
            carinspection.setStatus(status);

            carinspectionRepository.save(carinspection);
        }else {
            throw new RecordNotFoundException();
        }
    }
}
