package single.project.stakeapi.configuration.datasource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "transactionManagerStakeApi",  propagation = Propagation.REQUIRED, rollbackFor = { Exception.class})
public @interface StakeApiTransaction {
}