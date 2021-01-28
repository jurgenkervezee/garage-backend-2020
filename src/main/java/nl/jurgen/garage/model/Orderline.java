package nl.jurgen.garage.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "orderline")
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "carpart_id")
    Carpart carpart;

    @ManyToOne
    @JoinColumn(name = "carinspection_id")
    Carinspection carinspection;


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
