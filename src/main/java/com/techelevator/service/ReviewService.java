package com.techelevator.service;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Appointment;
import com.techelevator.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewDao reviewDao;

    public ReviewService(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    public List<Review> findAll(int officeId){

        return reviewDao.findAll(officeId); }

    public Review newReview(Review newReview, int id) {
        return reviewDao.newReview(newReview, id);
    }

}