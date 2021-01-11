package nl.jurgen.garage.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "car_part")
public class CarPart {

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
