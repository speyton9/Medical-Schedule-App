package com.techelevator.service;

import com.techelevator.dao.PatientAccountDao;
import com.techelevator.model.PatientAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientAccountService {

//    private PatientAccountDao patientAccountDao;
    private PatientAccountDao patientAccountDao;

    public PatientAccountService(PatientAccountDao patientAccountDao){
        this.patientAccountDao=patientAccountDao;
    }
    public List<PatientAccount> findAll() {
         return patientAccountDao.findAll();
    }

    public String getEmailByPatientId(Long id){
        return patientAccountDao.getEmailByPatientId(id);
    }
}
