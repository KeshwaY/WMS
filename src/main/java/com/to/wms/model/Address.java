package com.to.wms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collation = "address")
public class Address {

    @Id
    private String id;

    @NotBlank(message = "country has to be provided")
    private String country;

    @NotBlank(message = "city has to be provided")
    private String city;

    @NotBlank(message = "postcode has to be provided")
    private String postCode;

    @NotBlank(message = "street has to be provided")
    private String street;

    @NotBlank(message = "number has to be provided")
    private String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
