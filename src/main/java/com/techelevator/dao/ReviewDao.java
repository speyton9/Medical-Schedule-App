package com.techelevator.dao;

import com.techelevator.model.Appointment;
import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> findAll(int officeId);

    boolean create(int patientId, int officeId, String review);

    Review newReview(Review newReview, int id);
}