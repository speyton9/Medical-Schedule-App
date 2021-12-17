package com.techelevator.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PatientAccount {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;




    public PatientAccount() { }

    public PatientAccount(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        PatientAccount that = (PatientAccount) o;
        return id.equals(that.id) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "PatientAccount{" +
                "id=" + id +
                "firstName=" + firstName +
                ", lastName=" + lastName + '\'' +
                ", email=" + email +
                '}';
    }
}

