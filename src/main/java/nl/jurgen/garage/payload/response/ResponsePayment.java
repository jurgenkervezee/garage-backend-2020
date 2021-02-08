package nl.jurgen.garage.payload.response;

import nl.jurgen.garage.model.Orderline;

import java.util.List;

public class ResponsePayment {

    private List<Orderline> orderlines;
    private double totalPriceExVat;
    private double totalVatPrice;

    public ResponsePayment(List<Orderline> orderlines, double totalPriceExVat, double totalVatPrice) {
        this.orderlines = orderlines;
        this.totalPriceExVat = totalPriceExVat;
        this.totalVatPrice = totalVatPrice;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public double getTotalPriceExVat() {
        return totalPriceExVat;
    }

    public void setTotalPriceExVat(double totalPriceExVat) {
        this.totalPriceExVat = totalPriceExVat;
    }

    public double getTotalVatPrice() {
        return totalVatPrice;
    }

    public void setTotalVatPrice(double totalVatPrice) {
        this.totalVatPrice = totalVatPrice;
    }
}
