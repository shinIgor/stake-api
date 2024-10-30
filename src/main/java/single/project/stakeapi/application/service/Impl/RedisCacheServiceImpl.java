package single.project.stakeapi.application.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import single.project.stakeapi.application.model.transfer.exception.CommonErrorMessage;
import single.project.stakeapi.application.model.transfer.exception.CommonException;
import single.project.stakeapi.application.service.RedisCacheService;
import single.project.stakeapi.application.util.ValidCheck;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements RedisCacheService {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @ValidCheck
    public synchronized <T> T getValue(final String key, Class<T> clazz) {
        Object obj = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(obj)) {
            return null;
        }

        return objectMapper.convertValue(obj, clazz);
    }

    @Override
    @ValidCheck
    public <T> T getValue(String key, TypeReference<T> typeReference) {
        Object obj = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(obj)) {
            return null;
        }

        return objectMapper.convertValue(obj, typeReference);
    }

    @Override
    @ValidCheck
    public String getValue(String key) {
        Object obj = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(obj)) {
            return null;
        }

        return obj.toString();
    }

    @Override
    @ValidCheck
    public <T> T getAllValue(List<String> keys, TypeReference<T> typeReference) {
        List<Object> obj = redisTemplate.opsForValue().multiGet(keys);

        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }

        return objectMapper.convertValue(obj, typeReference);
    }
    @Override
    @ValidCheck
    public <T> T getAndDeleteValue(String key, Class<T> clazz) {
        Object obj = redisTemplate.opsForValue().getAndDelete(key);

        if (Objects.isNull(obj)) {
            return null;
        }

        return objectMapper.convertValue(obj, clazz);
    }

    @Override
    @ValidCheck
    public <T> T getAndDeleteValue(String key, TypeReference<T> typeReference) {
        Object obj = redisTemplate.opsForValue().getAndDelete(key);

        if (Objects.isNull(obj)) {
            return null;
        }

        return objectMapper.convertValue(obj, typeReference);
    }

    @Override
    @ValidCheck
    public String getAndDeleteValue(String key) {
        Object obj = redisTemplate.opsForValue().getAndDelete(key);

        if (Objects.isNull(obj)) {
            return null;
        }

        return obj.toString();
    }

    @Override
    @ValidCheck
    public void setValue(final String key, final Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    @ValidCheck
    public void setValue(final String key, final Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);

        this.setExpire(key, timeout, timeUnit);
    }

    @Override
    @ValidCheck
    public void setValue(String key, String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value);

        this.setExpire(key, timeout, timeUnit);
    }


    @Override
    @ValidCheck
    public void updateValue(String key, Object value) {
        Object obj = redisTemplate.opsForValue().get(key);

        if (!Objects.isNull(obj)) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    @Override
    @ValidCheck
    public void updateValue(String key, String value) {
        Object obj = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(obj))
            return;

        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    @ValidCheck
    public void setExpire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    @ValidCheck
    public void removeValue(String key) {
        redisTemplate.delete(key);
    }

    @Override
    @ValidCheck
    public void removeValues(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    @ValidCheck
    public Set<String> getKeys(String key) {
        Set<String> keys = new HashSet<>();

        ScanOptions option = ScanOptions.scanOptions()
                .match(key)
                .build();

        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();

        if (Objects.isNull(redisConnectionFactory)) {
            log.error("Redis connection factory is null");
            throw new CommonException(CommonErrorMessage.INTERNAL_SERVER_ERROR);
        }

        RedisConnection redisConnection = redisConnectionFactory.getConnection();

        Cursor<byte[]> cursor = redisConnection.scan(option);

        while (cursor.hasNext()) {
            keys.add(new String(cursor.next(), StandardCharsets.UTF_8));
        }
        return keys;
    }

    @Override
    @ValidCheck
    public Map<Object, Object> getHashKeys(String key, String fieldPattern) {
        return redisTemplate.execute(
                (RedisCallback<Map<Object, Object>>) connection -> {
                    Map<Object, Object> map = new HashMap<>();
                    ScanOptions options = ScanOptions.scanOptions()
                            .match(fieldPattern)
                            .count(300) // 적게하면 너무 scan 비율이 많아져서 오히려 1초이상 걸린다.
                            .build();

                    /**redis template의 opsForHash를 통해 scan을 진행하면
                     * 하나만 가져오는 이슈가 있다..
                     */
                    try (Cursor<Map.Entry<byte[], byte[]>> cursor = connection.hScan(key.getBytes(), options)) {
                        while (cursor.hasNext()) {
                            Map.Entry<byte[], byte[]> entry = cursor.next();

                            RedisSerializer<?> hashKeySerializer = redisTemplate.getHashKeySerializer();
                            RedisSerializer<?> hashValueSerializer = redisTemplate.getHashValueSerializer();

                            map.put(
                                    hashKeySerializer.deserialize(entry.getKey()),
                                    hashValueSerializer.deserialize(entry.getValue()));
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                    return map;
                });
    }

    @Override
    @ValidCheck
    public void setHash(String key, final String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    @ValidCheck
    public Long removeHashByKey(String key, final String field) {
        return redisTemplate.opsForHash().delete(key, field);
    }

    @Override
    @ValidCheck
    public Long getExpireSeconds(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
