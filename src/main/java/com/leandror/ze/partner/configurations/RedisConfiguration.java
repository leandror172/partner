package com.leandror.ze.partner.configurations;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.leandror.ze.partner.model.converters.BytesToPointConverter;
import com.leandror.ze.partner.model.converters.PointToBytesConverter;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis-14480.c253.us-central1-1.gce.cloud.redislabs.com",
                                                                                                 14480);
    redisStandaloneConfiguration.setPassword(RedisPassword.of("fN9pph31lHSxrt63XJaavzBh6VsZUuFb"));
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }
  
  @Bean
  public RedisCustomConversions redisCustomConversions(PointToBytesConverter pointToBytes,
                                                       BytesToPointConverter bytesToPoint) {
      return new RedisCustomConversions(Arrays.asList(pointToBytes, bytesToPoint));
  }

}
