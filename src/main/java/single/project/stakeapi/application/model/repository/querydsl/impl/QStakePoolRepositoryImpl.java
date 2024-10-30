package single.project.stakeapi.application.model.repository.querydsl.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import single.project.stakeapi.application.model.repository.querydsl.QStakePoolRepository;

@RequiredArgsConstructor
@Slf4j
public class QStakePoolRepositoryImpl implements QStakePoolRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
