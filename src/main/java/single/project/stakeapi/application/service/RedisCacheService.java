package single.project.stakeapi.application.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisCacheService {
    <T> T getValue(final String key, Class<T> clazz);
    <T> T getValue(final String key, TypeReference<T> typeReference);
    String getValue(final String key);

    <T> T getAllValue(List<String> keys, TypeReference<T> typeReference);
    <T> T getAndDeleteValue(final String key, Class<T> clazz);
    <T> T getAndDeleteValue(final String key, TypeReference<T> typeReference);
    String getAndDeleteValue(final String key);

    void setValue(final String key, final Object value);
    void setValue(final String key, final Object value, long timeout, TimeUnit timeUnit);

    void setValue(final String key, final String value, long timeout, TimeUnit timeUnit);

    void updateValue(String key, Object value);

    void updateValue(String key, String value);

    void setExpire(final String key, long timeout, TimeUnit timeUnit);

    void removeValue(final String key);

    void removeValues(final Collection<String> keys);

    Set<String> getKeys(final String key);

    Map<Object, Object> getHashKeys(final String key, final String fieldPattern);

    void setHash(final String key, final String field, final Object value);

    Long removeHashByKey(final String key, final String field);

    Long getExpireSeconds(final String key);
}
