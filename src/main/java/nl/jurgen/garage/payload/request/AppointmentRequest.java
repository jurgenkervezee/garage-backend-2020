package nl.jurgen.garage.payload.request;

import nl.jurgen.garage.model.EStatus;
import nl.jurgen.garage.model.Status;

import java.util.Date;

public class AppointmentRequest {

    private Date date;

    private Status status;

    public AppointmentRequest(Date date) {
        this.date = date;
        Status status = new Status();
        status.setName(EStatus.OPEN);
        this.status = status;
    }

    public AppointmentRequest() {
        Status status = new Status();
        status.setName(EStatus.OPEN);
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
