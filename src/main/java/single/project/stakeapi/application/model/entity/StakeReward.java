package single.project.stakeapi.application.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StakeReward {
    @Id
    @Column(name = "stake_reward_mt_id")
    Long id;

    @Column(name = "stake_pool_mt_id")
    Long stakePoolId;

    @Column(name = "reward_token_id")
    BigInteger rewardTokenId;

    @Column(name = "reward_amount")
    BigDecimal rewardAmount;

    @Column(name = "reward_duration")
    BigInteger rewardDuration;

    @Column(name = "reward_per_token")
    BigInteger rewardPerToken;

    @Column(name = "reward_start_at")
    LocalDateTime rewardStartAt;

    @Column(name = "reward_period_finish")
    LocalDateTime rewardPeriodFinish;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

}
