package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.model.Office;

@Component
public class JdbcOfficeDao2 implements OfficeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcOfficeDao2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Office> findAll() {
        List<Office> offices = new ArrayList<>();
        String sql = "SELECT * FROM offices";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Office office = mapRowToOffice(results);
            offices.add(office);
        }

        return offices;
    }

    @Override
    public Office getOfficeById(int officeId) {
        Office office=new Office();
        String sql = "SELECT * FROM offices WHERE office_id = ?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,officeId);
        if(results.next()){
            return mapRowToOffice(results);
        }else {
            throw new RuntimeException("Office is not found.");
        }

    }

    @Override
    public Office findOfficeByDoctor(String firstName, String lastName) {
        String sql = "SELECT * FROM offices o" +
                " JOIN doctor_office d ON o.office_id = d.office_id" +
                " JOIN doctor_accounts a ON a.doctor_id = d.doctor_id" +
                " WHERE first_name ILIKE '%'?'%' AND last_name ILIKE '%'?'%';";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, firstName, lastName);
        if(results.next()) {
            return mapRowToOffice(results);
        } else {
            throw new RuntimeException("Name was not found.");
        }
    }

    @Override
    public Office findOfficeByCity(String cityAddress) {
        String sql = "SELECT * FROM offices WHERE city_address LIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityAddress);

        if(results.next()){
            return mapRowToOffice(results);
        } else {
            throw new RuntimeException("city_address "+cityAddress+" was not found.");
        }

    }

    @Override
    public Office findOfficeByZip(String zipAddress) {
        String sql = "SELECT * FROM offices WHERE zip_address LIKE ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zipAddress);

        if(results.next()){
            return mapRowToOffice(results);
        } else {
            throw new RuntimeException("zip_address "+zipAddress+" was not found.");
        }

    }

    @Override
    public boolean newOffice(String address, String cityAddress, String stateAddress, String zipAddress, String phoneNumber, String startHour, String endHour, int price) {

        // create office
        String insertOffice = "insert into offices (address, cityAddress, stateAddress, zipAddress, phoneNumber, startHour, endHour, price) values (?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(insertOffice, address, cityAddress, stateAddress, zipAddress, phoneNumber, startHour, endHour, price);

        return true;

    }

    @Override
    public Office updateOffice(Office newOffice, int officeId) {

        String sql = "UPDATE offices" +
                " SET address = ?, city_address = ?, state_address = ?, zip_address = ?, phone_number = ?, start_hour = ?, end_hour = ?, price = ?" +
                " WHERE office_id = ?;";
       jdbcTemplate.update(sql, newOffice.getAddress(), newOffice.getCityAddress(), newOffice.getStateAddress(),  newOffice.getZipAddress(), newOffice.getPhoneNumber(), newOffice.getStartHour(),
       newOffice.getEndHour(), newOffice.getPrice(), officeId);

       return getOfficeById(officeId);

    }

    private Office mapRowToOffice(SqlRowSet rs) {

        Office office = new Office();
        office.setId(rs.getInt("office_id"));
        office.setName(rs.getString("name"));
        office.setAddress(rs.getString("address"));
        office.setCityAddress(rs.getString("city_address"));
        office.setStateAddress(rs.getString("state_address"));
        office.setZipAddress(rs.getString("zip_address"));
        office.setPhoneNumber(rs.getString("phone_number"));
        office.setStartHour(rs.getString("start_hour"));
        office.setEndHour(rs.getString("end_hour"));
        office.setPrice(rs.getInt("price"));

        return office;
    }
}