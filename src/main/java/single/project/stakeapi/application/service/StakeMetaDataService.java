package single.project.stakeapi.application.service;

import single.project.stakeapi.application.model.transfer.request.BasePageRequest;
import single.project.stakeapi.application.model.transfer.response.BasePageResponse;
import single.project.stakeapi.application.model.transfer.response.StakeMetaDataResponse;

public interface StakeMetaDataService {
    BasePageResponse<StakeMetaDataResponse> getStakePoolMetaData(BasePageRequest param);
}
