package nl.jurgen.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

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

    @Column(name = "status")
    private EStatus eStatus;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,
            mappedBy = "carinspection")
    Set<Orderline> orderlines;

    public Carinspection(){

    }

    public Carinspection(long id, Date date, EStatus status) {
        this.id = id;
        this.date = date;
        this.eStatus = status;
        seteStatus(EStatus.OPEN);
    }

    public void addCarpartToCarinspection(Carpart carpart, int amount){

        Orderline orderline = new Orderline(carpart, amount);
        orderlines.add(orderline);
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
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
