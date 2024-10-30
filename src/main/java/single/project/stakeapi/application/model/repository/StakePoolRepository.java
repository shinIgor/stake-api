package single.project.stakeapi.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import single.project.stakeapi.application.model.entity.StakePool;
import single.project.stakeapi.application.model.repository.querydsl.QStakePoolRepository;
import single.project.stakeapi.configuration.datasource.StakeApiDataSource;

@Repository
@StakeApiDataSource
public interface StakePoolRepository extends JpaRepository<StakePool, Long>, QStakePoolRepository {
}
