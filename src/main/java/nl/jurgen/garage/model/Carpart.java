package nl.jurgen.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "carpart")
public class Carpart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "stock_amount")
    private int stockAmount;

    @NotNull
    @Column(name = "price")
    private float price;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,
            mappedBy = "carpart")
    Set<Orderline> orderlines;

    public Set<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(Set<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
