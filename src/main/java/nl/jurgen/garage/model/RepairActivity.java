package nl.jurgen.garage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "repairactivity")
public class RepairActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column
    private double price;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,
            mappedBy = "repairactivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Orderline> orderline;

    public RepairActivity() {
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Orderline> getOrderline() {
        return orderline;
    }

    public void setOrderline(Set<Orderline> orderline) {
        this.orderline = orderline;
    }
}
