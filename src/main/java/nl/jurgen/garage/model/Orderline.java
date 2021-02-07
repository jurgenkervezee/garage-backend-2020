package nl.jurgen.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import nl.jurgen.garage.payload.request.OrderlineCustomRequest;

import javax.persistence.*;

@Entity
@Table(name = "orderline")
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @Column
    private double price;

    @Column
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carpart_id")
    private Carpart carpart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carinspection_id")
    private Carinspection carinspection;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "repairactivity_id")
    private RepairActivity repairactivity;

    public Orderline(){
    }

    public Orderline(Carpart carpart, int amount) {
        this.carpart = carpart;
        this.amount = amount;
        price = carpart.getPrice();
        description = carpart.getDescription();
    }

    public Orderline(String description, int amount, double price){
        this.description = description;
        this.amount = amount;
        this.price = price;
    }

    public Orderline(OrderlineCustomRequest orderlineCustomRequest){
        this.amount = orderlineCustomRequest.getAmount();
        this.description = orderlineCustomRequest.getDescription();
        this.price = orderlineCustomRequest.getPrice();
    }

    public RepairActivity getRepairactivity() {
        return repairactivity;
    }

    public void setRepairactivity(RepairActivity repairactivity) {
        this.repairactivity = repairactivity;
    }


    public long getId() {
        return id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Carinspection getCarinspection() {
        return carinspection;
    }

    public void setCarinspection(Carinspection carinspection) {
        this.carinspection = carinspection;
    }

    public Carpart getCarpart() {
        return carpart;
    }

    public void setCarpart(Carpart carpart) {
        this.carpart = carpart;
    }

}
