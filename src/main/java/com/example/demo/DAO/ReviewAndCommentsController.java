package com.example.demo.DAO;

import com.example.demo.DTO.AllReviewsDTO;
import com.example.demo.Models.ReviewComments;
import com.example.demo.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reviews")
public class ReviewAndCommentsController {

    private ReviewServices reviewsServices;
    int port;

    @Autowired
    public ReviewAndCommentsController(ReviewServices reviewsServices) {
        this.reviewsServices = reviewsServices;
    }

    //TODO: Change the PathVariable -- Richmond
    @GetMapping("/rev-by-package-id/{id}")
    public ResponseEntity<?> getAllReviewsByPackageId(@PathVariable Integer employee_package_id){
        AllReviewsDTO allReviews = reviewsServices.getAllReviewsByPackageID(employee_package_id);

        if(allReviews.getReviewDTOList().isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(allReviews);
    }

    @PostMapping("/new")
    public ResponseEntity createReview(@RequestBody ReviewComments newReviewComment){
        reviewsServices.createReview(newReviewComment);
        return ResponseEntity.accepted().build();
    }

    //update review
    @PatchMapping("/update")
    public ResponseEntity updateReview(@RequestBody ReviewComments newReviewComment){
        reviewsServices.createReview(newReviewComment);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/deleteReview/{review_comment_id}")
    public ResponseEntity deleteReview(@PathVariable int review_comment_id){
        ReviewComments reviewComment = reviewsServices.getReviewById(review_comment_id);
        if (reviewComment==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
       reviewsServices.deleteReview(reviewComment);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
