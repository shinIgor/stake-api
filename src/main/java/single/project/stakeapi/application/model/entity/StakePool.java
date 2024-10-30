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
import single.project.stakeapi.application.model.entity.base.BaseEntity;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StakePool extends BaseEntity {
    @Id
    @Column(name = "stake_pool_mt_id")
    Long id;

    @Column(name = "contract_mt_id")
    BigInteger contractId;

    @Column(name = "staking_token_id")
    BigInteger stakingTokenId;

    @Column(name = "contract_admin_mt_id")
    BigInteger contractAdminId;

    @Size(max = 50)
    @Column(name = "contract_address")
    String contractAddress;
}
