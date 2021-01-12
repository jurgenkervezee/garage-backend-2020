package nl.jurgen.garage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number_plate")
    private String numberPlate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @JsonIgnore
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    public Car(){
    }

    public Car(String numberPlate, String brand, String model) {
        this.numberPlate = numberPlate;
        this.brand = brand;
        this.model = model;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
