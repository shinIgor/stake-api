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

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chain extends BaseEntity {
    @Id
    @Column(name = "chain_mt_id")
    Long id;

    @Size(max = 50)
    @Column(name = "network_name")
    String networkName;

    @Column(name = "network_id")
    Long networkId;
}
