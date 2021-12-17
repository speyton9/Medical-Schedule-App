package com.techelevator.dao;

import com.techelevator.model.Office;

import java.util.List;

public interface OfficeDao {

    List<Office> findAll();

    Office getOfficeById(int officeId);

    Office findOfficeByDoctor(String firstName, String lastName);

    Office findOfficeByCity(String cityAddress);

    Office findOfficeByZip(String zipAddress);

    Office updateOffice(Office newOffice, int officeId);

    boolean newOffice(String address, String cityAddress, String stateAddress, String zipAddress, String phoneNumber, String startHour, String endHour, int price);
}