package com.techelevator.controller;

import com.techelevator.model.Office;
import com.techelevator.service.OfficeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OfficeController {

        public OfficeService officeService;

        public OfficeController(OfficeService officeService) {
            this.officeService = officeService;
        }

        @RequestMapping(path = "/offices", method = RequestMethod.GET)
        public List<Office> findAll() {
            return officeService.findAll();
        }

        @RequestMapping(path = "/offices/{officeId}",method = RequestMethod.GET)
        public Office getOfficeById(@PathVariable int officeId) {
        return officeService.getOfficeById(officeId);
        }

        @RequestMapping(path = "/offices/{officeId}", method = RequestMethod.PUT)
        public Office updateOffice(@RequestBody Office newOffice, @PathVariable int officeId) {
                return officeService.updateOffice(newOffice, officeId);
        }





}
