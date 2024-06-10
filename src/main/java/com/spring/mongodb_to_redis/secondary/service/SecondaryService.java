package com.spring.mongodb_to_redis.secondary.service;

import com.spring.mongodb_to_redis.primary.entity.UserDetails;
import com.spring.mongodb_to_redis.primary.repository.PrimeRepository;
import com.spring.mongodb_to_redis.secondary.entity.User;
import com.spring.mongodb_to_redis.secondary.repository.SecondaryRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SecondaryService {

    private SecondaryRepository secondaryRepository;

    private PrimeRepository primeRepository;

    public SecondaryService(SecondaryRepository secondaryRepository,PrimeRepository primeRepository) {
        this.secondaryRepository = secondaryRepository;
        this.primeRepository=primeRepository;
    }

    public UserDetails paste(int id){
        if(primeRepository.findById(id).isPresent()){
            UserDetails foundedUser=primeRepository.findById(id).get();
            User obj=new User();
            obj.setId(foundedUser.getId());
            obj.setName(foundedUser.getName());
            obj.setAge(foundedUser.getAge());

            secondaryRepository.save(obj);
            return foundedUser;
        }else{
            throw new NoSuchElementException("No Such named person");
        }
    }

    public Iterable<User> retrieveAll(){
        try {
            Iterable<User> userList=secondaryRepository.findAll();
            return userList;
        }catch (NullPointerException e){
            throw new NullPointerException("No elements to retrieve");
        }

    }
}
