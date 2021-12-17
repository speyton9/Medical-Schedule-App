package com.techelevator.controller;

import com.techelevator.model.DoctorAccount;
import com.techelevator.model.PatientAccount;
import com.techelevator.service.PatientAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class PatientAccountController {
    private PatientAccountService patientAccountService;
    public PatientAccountController(PatientAccountService patientAccountService){
        this.patientAccountService=patientAccountService;
    }
    @RequestMapping(path ="/patients",method = RequestMethod.GET)
    public List<PatientAccount> findAll() {
        return  patientAccountService.findAll();
    }

    @RequestMapping(path ="/patients/{id}",method = RequestMethod.GET)
    public String getEmailByPatientId(@PathVariable Long id){
        return patientAccountService.getEmailByPatientId(id);
    }


}
