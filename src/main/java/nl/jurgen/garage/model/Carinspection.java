package nl.jurgen.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "carinspection")
public class Carinspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "date_inspection")
    private Date date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @OneToOne()
    @JoinColumn(name = "status", referencedColumnName = "id")
    private Status status;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,
            mappedBy = "carinspection")
    Set<Orderline> orderlines;

    public Carinspection(){
    }

    public Carinspection(long id, Date date, Status status) {
        this.id = id;
        this.date = date;
        this.status = status;

    }

    public void addCarpartToCarinspection(Carpart carpart, int amount){

        Orderline orderline = new Orderline(carpart, amount);
        orderlines.add(orderline);
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Set<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(Set<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addOrderline(Orderline orderline) {
        orderlines.add(orderline);
    }
}
