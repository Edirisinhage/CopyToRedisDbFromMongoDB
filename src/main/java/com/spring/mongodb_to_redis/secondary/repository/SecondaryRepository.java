package com.spring.mongodb_to_redis.secondary.repository;

import com.spring.mongodb_to_redis.secondary.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface SecondaryRepository extends CrudRepository<User,Integer> {

}
