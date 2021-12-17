package com.techelevator.dao;

import com.techelevator.model.DoctorAccount;

import java.util.List;

public interface DoctorAccountDao {

    List<DoctorAccount> findAll();

    DoctorAccount getAccountByDoctorId(int doctorId);

    DoctorAccount getAccountById(int userId);

    DoctorAccount findByFirstName(String firstName);

    DoctorAccount findByLastName(String lastName);

    int findAccountIdByFirstName(String firstName);

    String viewAvailabilityByDoctor(String firstName, String lastName);

    void updateAvailability(DoctorAccount doctorAccount, int doctorId);

    boolean create(String firstName, String lastName, String email, String username);

    DoctorAccount getAccountByUserId(int userId);
}