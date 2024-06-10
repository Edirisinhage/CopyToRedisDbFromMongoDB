package com.spring.mongodb_to_redis.primary.controller;

import com.spring.mongodb_to_redis.primary.entity.UserDetails;
import com.spring.mongodb_to_redis.primary.entity.dto.Response;
import com.spring.mongodb_to_redis.primary.repository.PrimeRepository;
import com.spring.mongodb_to_redis.primary.service.PrimaryService;
import com.spring.mongodb_to_redis.secondary.service.SecondaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/mongo")
public class Controller {

    private PrimaryService primaryService;

    private SecondaryService secondaryService;

    public Controller(PrimaryService primaryService,SecondaryService secondaryService) {
        this.primaryService =primaryService ;
        this.secondaryService=secondaryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> add(@RequestBody UserDetails userDetails){
        try{
            Response response=Response.builder()
                    .status("SUCCESS")
                    .object(userDetails)
                    .message(primaryService.save(userDetails))
                    .build();
           return ResponseEntity.ok(response);
        }catch (Exception e){
            Response response=Response.builder()
                    .status("Failed")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/get/{id}")
    public UserDetails get(@PathVariable int id){

        return primaryService.find(id);

    }

    @PostMapping("/paste/{id}")
    public ResponseEntity<Response> pasteToRedis(@PathVariable int id){
        try{
            Response response=Response.builder()
                    .status("SUCCESS")
                    .object(secondaryService.paste(id))
                    .message("Successfully Paste Data from MongoDB To Redis")
                    .build();
            return ResponseEntity.ok(response);
        }catch (NoSuchElementException e){
            Response response=Response.builder()
                    .status("Failed")
                    .message("Failed Pasting Data from MongoDB To Redis")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
    @GetMapping("/get-all-redis")
    public ResponseEntity<Response> retrieveAllFromRedis(){
        try{
            Response response=Response.builder()
                    .status("SUCCESS")
                    .message("Successfully retrieve")
                    .object(secondaryService.retrieveAll())
                    .build();
            return ResponseEntity.ok(response);
        }catch (NullPointerException e){
            Response response=Response.builder()
                    .status("Failed")
                    .message("Data retrieval failed")
                    .object(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }


}
