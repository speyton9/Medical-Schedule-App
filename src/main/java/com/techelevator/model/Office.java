package com.techelevator.model;

import java.util.Objects;

public class Office {
    //Instance variables
    private int officeId;
    private String name;
    private String address;
    private String cityAddress;
    private String stateAddress;
    private String zipAddress;
    private String phoneNumber;
    private String startHour;
    private String endHour;
    private int price;

    //Constructors
    public Office() {
    }

    public Office(int officeId, String name, String address, String cityAddress, String stateAddress, String zipAddress, String phoneNumber, String startHour, String endHour, int price) {
        this.officeId = officeId;
        this.name = name;
        this.address = address;
        this.cityAddress = cityAddress;
        this.stateAddress = stateAddress;
        this.zipAddress = zipAddress;
        this.phoneNumber = phoneNumber;
        this.startHour = startHour;
        this.endHour = endHour;
        this.price = price;
    }

    //Getters and Setters
    public int getId() {
        return officeId;
    }

    public void setId(int officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getStateAddress() {
        return stateAddress;
    }

    public void setStateAddress(String stateAddress) {
        this.stateAddress = stateAddress;
    }

    public String getZipAddress() {
        return zipAddress;
    }

    public void setZipAddress(String zipAddress) {
        this.zipAddress = zipAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //Methods
    @Override
    public String toString() {
        return "Office{" +
                "name=" + name +
                ", address=" + address + '\'' +
                ", cityAddress=" + cityAddress + '\'' +
                ", stateAddress=" + stateAddress + '\'' +
                ", zipAddress=" + zipAddress + '\'' +
                ", phoneNumber=" + phoneNumber + '\'' +
                ", startHour=" + startHour + '\'' +
                ", endHour=" + endHour + '\'' +
                ", price=" + price +
                '}';
    }
}