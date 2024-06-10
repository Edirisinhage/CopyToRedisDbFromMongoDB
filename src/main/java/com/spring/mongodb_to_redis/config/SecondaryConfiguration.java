package com.spring.mongodb_to_redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(
        basePackages = "com.spring.mongodb_to_redis.secondary.repository",
        redisTemplateRef="secondaryRedisTemplate"
)
public class SecondaryConfiguration {

    @Value("${spring.data.redis.secondary.host}")
    private String localHost;
    @Value("${spring.data.redis.secondary.password}")
    private String password;
    @Value("${spring.data.redis.secondary.port}")
    private int port;


    @Bean(name = "jedisFactory")
    public LettuceConnectionFactory jedisConnectionFactory() {
        LettuceConnectionFactory jedisConFactory
                = new LettuceConnectionFactory();
        jedisConFactory.setHostName(localHost);
        jedisConFactory.setPassword(password);
        jedisConFactory.setDatabase(1);
        jedisConFactory.setPort(port);
        return jedisConFactory;
    }

    @Bean(name = "secondaryRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("jedisFactory") LettuceConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }


}
