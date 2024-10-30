package single.project.stakeapi.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import single.project.stakeapi.application.model.entity.Abi;
import single.project.stakeapi.configuration.datasource.StakeApiDataSource;

@Repository
@StakeApiDataSource
public interface AbiRepository extends JpaRepository<Abi, Long> {
}
