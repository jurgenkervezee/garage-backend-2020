package nl.jurgen.garage.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Status {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long id;

    @Enumerated(EnumType.STRING)
    private EStatus name;

    public Status() {
    }

    public Status(EStatus name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public EStatus getName() {
        return name;
    }

    public void setName(EStatus name) {
        this.name = name;
    }
}
