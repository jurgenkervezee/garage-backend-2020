package nl.jurgen.garage.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class CarInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "date_inspection")
    private Date date;

    @NotNull
    @Column(name = "client_id")
    private Client client;


    public CarInspection(){

    }

    public CarInspection(long id, Date date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }
}
