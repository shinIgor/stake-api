package single.project.stakeapi.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import single.project.stakeapi.application.model.entity.Contract;
import single.project.stakeapi.configuration.datasource.StakeApiDataSource;

import java.math.BigInteger;

@Repository
@StakeApiDataSource
public interface ContractRepository extends JpaRepository<Contract, BigInteger> {
}
