package com.spring.mongodb_to_redis.primary.service;

import com.spring.mongodb_to_redis.primary.entity.UserDetails;
import com.spring.mongodb_to_redis.primary.repository.PrimeRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class PrimaryService {

    private PrimeRepository primeRepository;


    public PrimaryService(PrimeRepository primeRepository) {
        this.primeRepository = primeRepository;
    }

    Logger logger=Logger.getLogger(String.valueOf(PrimaryService.class));
    public UserDetails find(int id){
        if(primeRepository.findById(id).isPresent()){
            return primeRepository.findById(id).get();
        }else{
            throw new NoSuchElementException("No such name in the List");
        }
    }

    public String save(UserDetails details) throws Exception {
        logger.info(details.toString());
        try{
            primeRepository.save(details);
          return "Successfully saved";
        }catch (Exception e){
            throw new Exception("Data violation issue");
        }

    }
}
