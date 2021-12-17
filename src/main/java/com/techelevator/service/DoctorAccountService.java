package com.techelevator.service;

import com.techelevator.dao.DoctorAccountDao;
import com.techelevator.model.DoctorAccount;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DoctorAccountService {

    private DoctorAccountDao doctorAccountDao;

    public DoctorAccountService(DoctorAccountDao doctorAccountDao){
        this.doctorAccountDao = doctorAccountDao;
    }

    //Service to view the availability of the doctor by name
    public String viewAvailabilityByDoctor(String firstName, String lastName) {
        return doctorAccountDao.viewAvailabilityByDoctor(firstName, lastName);
    }

    //Service to update the availability of the doctor by id (doctor that is logged in)
    public void updateAvailability(DoctorAccount doctorAccount, int doctorId){
        doctorAccountDao.updateAvailability(doctorAccount, doctorId);
    }

    public DoctorAccount getAccountByDoctorId(int doctorId){
        return doctorAccountDao.getAccountByDoctorId(doctorId);
    }

    public List<DoctorAccount> findAll(){ return doctorAccountDao.findAll(); }

    public DoctorAccount getAccountByUserId(int userId){
        return doctorAccountDao.getAccountByUserId(userId);
    }

}