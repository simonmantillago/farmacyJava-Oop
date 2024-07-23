package com.farmacysystem.customer.domain.entity;

public class Customer {
    private String identificationNumber;
    private String typeID;
    private String firstName;
    private String lastName;
    private int age;
    private String birthDate;
    private int cityID;
    private int neighborhoodID;
    private String registrationDate;
    
    public Customer() {
    }
    
    public Customer(String identificationNumber, String typeID, String firstName, String lastName, int age, String birthDate, int cityID, int neighborhoodID, String registrationDate) {
        this.identificationNumber = identificationNumber;
        this.typeID = typeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.cityID = cityID;
        this.neighborhoodID = neighborhoodID;
        this.registrationDate = registrationDate;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getNeighborhoodID() {
        return neighborhoodID;
    }

    public void setNeighborhoodID(int neighborhoodID) {
        this.neighborhoodID = neighborhoodID;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
