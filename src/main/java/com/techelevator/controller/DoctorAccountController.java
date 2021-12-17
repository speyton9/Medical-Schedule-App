package com.techelevator.controller;

import com.techelevator.model.DoctorAccount;
import com.techelevator.service.DoctorAccountService;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DoctorAccountController {

    DoctorAccountService doctorAccountService;

    public DoctorAccountController(DoctorAccountService doctorAccountService){
        this.doctorAccountService = doctorAccountService;
    }
//      Is this not needed??
//    @RequestMapping(path= "/doctors", method = RequestMethod.GET)
//    public int viewAvailabilityByDoctor(String firstName, String lastName){ return doctorAccountService.viewAvailabilityByDoctor(firstName, lastName); }

    @RequestMapping(path= "/doctors", method = RequestMethod.GET)
    public List<DoctorAccount> findAll(){ return doctorAccountService.findAll(); }

    @RequestMapping(path= "/updateavailability/{doctorId}", method = RequestMethod.PUT)
    public void updateAvailability(@RequestBody DoctorAccount doctorAccount, @PathVariable int doctorId){
        doctorAccountService.updateAvailability(doctorAccount, doctorId);
    }

    @RequestMapping(path= "/doctors/{doctorId}", method = RequestMethod.GET)
    public DoctorAccount getAccountByDoctorId(@PathVariable int doctorId){
        return doctorAccountService.getAccountByDoctorId(doctorId);
    }

    @RequestMapping(path= "/doctor/{userId}", method = RequestMethod.GET)
    public DoctorAccount getAccountByUserId(@PathVariable int userId){
        return doctorAccountService.getAccountByUserId(userId);
    }
}