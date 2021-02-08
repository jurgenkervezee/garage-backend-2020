package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Orderline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    private static final double vatPercentage = 0.19;

    public Double getPriceExVatForRepairByCarinspection(List<Orderline> orderlines) {

        double priceExVat = 0;

        for(Orderline orderline: orderlines){
            double price = orderline.getPrice() * orderline.getAmount();
            priceExVat = priceExVat + price;
        }
        return Math.round(priceExVat*100)/100.0d;
    }

    public Double calculateVat(Double priceExVat){
        return Math.round((priceExVat*vatPercentage)*100)/100.0d;
    }
}
