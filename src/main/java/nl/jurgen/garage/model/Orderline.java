package nl.jurgen.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

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
