package com.techelevator.model;

import java.util.Objects;

public class DoctorAccount {


    private int id;
    private String firstName;
    private String lastName;
    private String clockIn;
    private String clockOut;
    private String email;



    public DoctorAccount() { }

    public DoctorAccount(int id, String firstName, String lastName, String clockIn, String clockOut, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getClockIn() { return clockIn; }

    public void setClockIn(String clockIn) { this.clockIn = clockIn; }

    public String getClockOut() { return clockOut; }

    public void setClockOut(String clockOut) { this.clockOut = clockOut; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorAccount that = (DoctorAccount) o;
        return id == that.id && firstName.equals(that.firstName) && lastName.equals(that.lastName)
                && clockIn.equals(that.clockIn) && clockOut.equals(that.clockOut) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "DoctorAccount{" +
                "id=" + id +
                "firstName=" + firstName +
                ", lastName=" + lastName + '\'' +
                ", clockIn=" + clockIn + '\'' +
                ", clockOut=" + clockOut + '\'' +
                ", email=" + email + '}';
    }
}
