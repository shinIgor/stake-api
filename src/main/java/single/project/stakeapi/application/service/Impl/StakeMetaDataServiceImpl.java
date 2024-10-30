package single.project.stakeapi.application.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import single.project.stakeapi.application.model.repository.StakePoolRepository;
import single.project.stakeapi.application.model.repository.StakeRewardRepository;
import single.project.stakeapi.application.model.transfer.response.BasePageResponse;
import single.project.stakeapi.application.model.transfer.response.StakeMetaDataResponse;
import single.project.stakeapi.application.service.StakeMetaDataService;

@Slf4j
@Service
@RequiredArgsConstructor
public class StakeMetaDataServiceImpl implements StakeMetaDataService {
    private final StakePoolRepository stakePoolRepository;
    private final StakeRewardRepository stakeRewardRepository;

    public BasePageResponse<StakeMetaDataResponse> getStakePoolMetaData(){

    }
}
