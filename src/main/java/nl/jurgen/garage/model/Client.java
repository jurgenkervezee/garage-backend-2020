package nl.jurgen.garage.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "client_nr")
    private String clientNumber;

    @OneToOne
    private Address address;

    public long getId() {
        return id;
    }

    public Client(){
    }
    public Client(String firstName, String lastName, String clientNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientNumber = clientNumber;
        System.out.println("Client created");
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
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }
}
