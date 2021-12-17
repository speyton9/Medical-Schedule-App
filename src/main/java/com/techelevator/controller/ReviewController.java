package com.techelevator.controller;

import com.techelevator.model.Appointment;
import com.techelevator.model.Office;
import com.techelevator.model.Review;
import com.techelevator.service.ReviewService;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @RequestMapping(path= "/reviews/{officeId}", method = RequestMethod.GET)
    public List<Review> findAll(@PathVariable int officeId) { return reviewService.findAll(officeId); }

    @RequestMapping(path = "/review/{id}", method = RequestMethod.POST)
    public Review newReview(@RequestBody Review newReview, @PathVariable int id) {
        return reviewService.newReview(newReview, id);
    }

}
