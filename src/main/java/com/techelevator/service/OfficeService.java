package com.techelevator.service;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Office;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    private OfficeDao officeDao;

    public OfficeService(OfficeDao officeDao) {
        this.officeDao = officeDao;

    }

    public List<Office> findAll() {
        return officeDao.findAll();
    }

    public Office getOfficeById(int officeId) {
        return officeDao.getOfficeById(officeId);
    }

    public Office updateOffice(Office newOffice, int officeId) {return officeDao.updateOffice(newOffice, officeId);}


}
