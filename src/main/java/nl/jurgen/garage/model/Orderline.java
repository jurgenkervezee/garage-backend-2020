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
    Carpart carpart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carinspection_id")
    Carinspection carinspection;

    public Orderline(){
    }

    public Orderline(Carpart carpart, int amount) {
        this.carpart = carpart;
        this.amount = amount;
        price = carpart.getPrice();
        description = carpart.getDescription();
    }

    public Orderline(OrderlineCustomRequest orderlineCustomRequest){
        this.amount = orderlineCustomRequest.getAmount();
        this.description = orderlineCustomRequest.getDescription();
        this.price = orderlineCustomRequest.getPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
