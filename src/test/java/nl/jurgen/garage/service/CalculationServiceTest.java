package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Orderline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class CalculationServiceTest {

    private CalculationService calculationService = new CalculationService();

    @Test
    void getPriceExVatForRepairByCarinspection() {
        //Given
        List<Orderline> orderlines = new ArrayList<>();
        orderlines.add(new Orderline("Henk", 2, 4.99));
        //When
        double result = calculationService.getPriceExVatForRepairByCarinspection(orderlines);
        //Then
        Assertions.assertEquals(9.98, result);
    }

    @Test
    void calculateVat() {
        //Given
        double priceExVat = 15.00;
        //When
        double result = calculationService.calculateVat(priceExVat);
        //Then
        Assertions.assertEquals(2.85, result);
    }
}