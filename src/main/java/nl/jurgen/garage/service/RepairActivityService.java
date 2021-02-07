package nl.jurgen.garage.service;

import nl.jurgen.garage.exception.DatabaseErrorException;
import nl.jurgen.garage.exception.RecordNotFoundException;
import nl.jurgen.garage.model.RepairActivity;
import nl.jurgen.garage.repository.RepairActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairActivityService {

    @Autowired
    RepairActivityRepository repairActivityRepository;

    public List<RepairActivity> getAllRepairActivities() {
        return repairActivityRepository.findAll();
    }

    public RepairActivity getRepairActivityById(long id) {

        if (repairActivityRepository.existsById(id)) {
            return repairActivityRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Long saveRepairActivity(RepairActivity repairActivity) {
        return repairActivityRepository.save(repairActivity).getId();
    }

    public void deleteRepairActivity(long id) {
        if (repairActivityRepository.existsById(id)) {
            repairActivityRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public RepairActivity updateRepairActivityById(long id, RepairActivity repairActivity) {
        if (repairActivityRepository.existsById(id)) {
            try {
                RepairActivity existingRepairActivity = repairActivityRepository.findById(id).orElse(null);
                existingRepairActivity.setDescription(repairActivity.getDescription());
                existingRepairActivity.setPrice(repairActivity.getPrice());
                repairActivityRepository.save(existingRepairActivity);

                return existingRepairActivity;
            } catch (Exception e) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }
}
