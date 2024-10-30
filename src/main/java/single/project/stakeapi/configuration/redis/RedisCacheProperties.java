package single.project.stakeapi.configuration.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "application.redis.cache")
public class RedisCacheProperties {
    private String host = "localhost";
    private Integer port = 6379;
    private Integer database = 0;
    private Duration timeout = Duration.ofSeconds(3L);
}
