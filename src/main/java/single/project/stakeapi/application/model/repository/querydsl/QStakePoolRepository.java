package single.project.stakeapi.application.model.repository.querydsl;

import single.project.stakeapi.application.model.entity.StakePool;
import single.project.stakeapi.application.model.transfer.request.BasePageRequest;

import java.util.List;

public interface QStakePoolRepository {
    List<StakePool> getStakePoolData(BasePageRequest paramBasePageRequest);
}
