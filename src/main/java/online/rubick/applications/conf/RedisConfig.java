package online.rubick.applications.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


    /**
     * 设置key、value的序列化
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public RedisTemplate<String, Object> redisTemplate( RedisConnectionFactory factory ) {
        RedisTemplate<String,Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer =new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS,true) ;
        objectMapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS,true) ;
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate) ;

        return cacheManager;
    }
}
