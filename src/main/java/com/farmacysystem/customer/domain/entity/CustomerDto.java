package com.farmacysystem.customer.domain.entity;

public class CustomerDto {
    private String identificationNumber;
    private String typeDescription;
    private String firstName;
    private String lastName;
    private int age;
    private String birthDate;
    private int cityID;
    private int NeighborhoodID;
    private String cityName;
    private String neighborhoodName;
    

    public CustomerDto(String identificationNumber, String typeDescription, String firstName, String lastName, int age,
            String birthDate, int cityID, int neighborhoodID, String cityName, String neighborhoodName) {
        this.identificationNumber = identificationNumber;
        this.typeDescription = typeDescription;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.cityID = cityID;
        this.NeighborhoodID = neighborhoodID;
        this.cityName = cityName;
        this.neighborhoodName = neighborhoodName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
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
        return NeighborhoodID;
    }

    public void setNeighborhoodID(int neighborhoodID) {
        NeighborhoodID = neighborhoodID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    

   
}
