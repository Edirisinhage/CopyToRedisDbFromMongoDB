package com.spring.mongodb_to_redis.primary.entity.dto;

import com.spring.mongodb_to_redis.primary.entity.UserDetails;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String status;
    private Object object;
    private String message;
}
