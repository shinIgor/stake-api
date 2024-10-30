package single.project.stakeapi.application.model.repository.querydsl.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import single.project.stakeapi.application.model.repository.querydsl.QAbiRepository;

@RequiredArgsConstructor
@Slf4j
public class QAbiRepositoryImpl implements QAbiRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
