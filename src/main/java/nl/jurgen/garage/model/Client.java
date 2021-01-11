package nl.jurgen.garage.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "telephone_number")
    private String telephoneNumber;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="client")
    private Address address;

    public Client(){
    }

    public Client(String firstName, String lastName, String telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        System.out.println("Client created");
    }


    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClientNumber() {
        return telephoneNumber;
    }

    public void setClientNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


}
