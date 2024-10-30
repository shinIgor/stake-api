package single.project.stakeapi.application.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import single.project.stakeapi.application.model.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staker extends BaseEntity {
    @Id
    @Column(name = "stake_staker_ut_id")
    Long id;

    @Column(name = "stake_pool_mt_id")
    Long stakePoolId;

    @Size(max = 50)
    @Column(name = "wallet_address")
    String walletAddress;

    @Column(name = "stake_amount")
    BigDecimal stakeAmount;

    @Column(name = "total_reward_paid")
    BigDecimal totalRewardPaid;

    @Column(name = "stake_flag")
    Boolean stakeFlag;

    @Column(name = "stake_at")
    LocalDateTime stakeAt;

    @Column(name = "unstake_at")
    LocalDateTime unstakeAt;
}
