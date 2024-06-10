package com.spring.mongodb_to_redis.primary.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserDetails {

    @Id
    private int id;
    @Indexed(unique = true)
    private String name;

    private int age;
}
