package single.project.stakeapi.configuration.datasource;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StakeApiDataSource {
}
