package com.techelevator.dao;

import com.techelevator.model.PatientAccount;

import java.util.List;

public interface PatientAccountDao {

    List<PatientAccount> findAll();

    String getEmailByPatientId(Long id);

    PatientAccount findByFirstName(String firstName);

    PatientAccount findByLastName(String lastName);

    int findAccountIdByFirstName(String firstName);

    boolean create(String firstName, String lastName, String email, String username);

}
