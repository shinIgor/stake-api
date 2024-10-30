package single.project.stakeapi.application.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Table(name = "stake_reward_mt")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "stake_pool_mt_id", referencedColumnName = "stake_pool_mt", insertable = false, updatable = false)
    StakePool stakePool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "reward_token_id", referencedColumnName = "contract_mt", insertable = false, updatable = false)
    Contract rewardERC20;
}
