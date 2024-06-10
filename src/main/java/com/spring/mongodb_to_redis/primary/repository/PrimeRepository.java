package com.spring.mongodb_to_redis.primary.repository;

import com.spring.mongodb_to_redis.primary.entity.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PrimeRepository extends MongoRepository<UserDetails,Integer> {
    Optional<UserDetails> findByName(String name);
}
