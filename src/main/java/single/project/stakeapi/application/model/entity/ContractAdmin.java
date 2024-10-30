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
public class ContractAdmin extends BaseEntity {
    @Id
    @Column(name = "contract_admin_mt_id")
    BigInteger id;

    @Size(max = 50)
    @Column(name = "public_address")
    String publicAddress;

    @Column(name = "private_address_key")
    String privateAddressKey;

    @Column(name = "private_address_value")
    String privateAddressValue;
}
