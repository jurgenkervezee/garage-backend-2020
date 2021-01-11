package nl.jurgen.garage.service;

import nl.jurgen.garage.model.CarPart;

import java.util.List;

public interface CarPartService {

    List<CarPart> getAllCarParts();
    void updateStockAmount(long id, int amount);
    CarPart getCarPartById(long id);
    CarPart addCarpart(CarPart carPart);
}
