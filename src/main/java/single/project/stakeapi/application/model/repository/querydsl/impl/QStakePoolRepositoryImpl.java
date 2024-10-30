package single.project.stakeapi.application.model.repository.querydsl.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import single.project.stakeapi.application.model.entity.StakePool;
import single.project.stakeapi.application.model.repository.querydsl.QStakePoolRepository;
import single.project.stakeapi.application.model.transfer.request.BasePageRequest;
import single.project.stakeapi.application.util.ValidCheck;

import java.util.List;

import static single.project.stakeapi.application.model.entity.QStakePool.stakePool;

@RequiredArgsConstructor
@Slf4j
public class QStakePoolRepositoryImpl implements QStakePoolRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @ValidCheck
    public List<StakePool> getStakePoolData(BasePageRequest param) {
        Pageable pageable = param.makePageable();
        return jpaQueryFactory.selectFrom(stakePool)
                .leftJoin(stakePool.stakeERC20)
                .fetchJoin()
                .leftJoin(stakePool.contractAdmin)
                .fetchJoin()
                .leftJoin(stakePool.contract)
                .fetchJoin()
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }
}
