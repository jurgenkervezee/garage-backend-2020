package nl.jurgen.garage.model;

import nl.jurgen.garage.payload.request.RegisterUserRequest;

import javax.persistence.Column;

public class ClientBuilder {

    //Client
    private String firstName;
    private String lastName;
    private String clientNumber;
    //Address
    private String streetName;
    private String houseNumber;
    private String houseNumberAddition;
    private String postalCode;
    private String homeTown;

    public ClientBuilder(RegisterUserRequest registerUserRequest){
        this.firstName = registerUserRequest.getFirstName();
        this.lastName = registerUserRequest.getLastName();
        this.clientNumber = registerUserRequest.getClientNumber();
        this.streetName = registerUserRequest.getStreetName();
        this.houseNumber = registerUserRequest.getHouseNumber();
        this.houseNumberAddition = registerUserRequest.getHouseNumberAddition();
        this.postalCode = registerUserRequest.getPostalCode();
        this.homeTown = registerUserRequest.getHomeTown();

    }

    public ClientBuilder withHousenumberAddition(RegisterUserRequest registerUserRequest){
        this.houseNumberAddition = registerUserRequest.getHouseNumberAddition();
        return this;
    }

    public Client buildClient(){

        return new Client(firstName, lastName, clientNumber);
    }

    public Address buildAddress(){

        return new Address(streetName, houseNumber, houseNumberAddition, postalCode, homeTown);
    }

}
