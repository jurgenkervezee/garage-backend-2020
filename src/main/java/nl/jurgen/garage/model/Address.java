package nl.jurgen.garage.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "street_name" )
    private String streetName;

    @Column(name = "house_number" )
    private String houseNumber;

    @Column(name = "house_number_add" )
    private String houseNumberAddition;

    @Column(name = "postal_code" )
    private String postalCode;

    @Column(name = "home_town" )
    private String homeTown;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    public void setHouseNumberAddition(String houseNumberAddition) {
        this.houseNumberAddition = houseNumberAddition;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
