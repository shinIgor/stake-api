package single.project.stakeapi.application.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import single.project.stakeapi.application.model.define.DefineRedisCacheKey;
import single.project.stakeapi.application.model.entity.StakePool;
import single.project.stakeapi.application.model.repository.StakePoolRepository;
import single.project.stakeapi.application.model.repository.StakeRewardRepository;
import single.project.stakeapi.application.model.transfer.request.BasePageRequest;
import single.project.stakeapi.application.model.transfer.response.BasePageResponse;
import single.project.stakeapi.application.model.transfer.response.StakeMetaDataResponse;
import single.project.stakeapi.application.service.RedisCacheService;
import single.project.stakeapi.application.service.StakeMetaDataService;
import single.project.stakeapi.application.util.ValidCheck;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StakeMetaDataServiceImpl implements StakeMetaDataService {
    private final StakePoolRepository stakePoolRepository;
    private final StakeRewardRepository stakeRewardRepository;
    private final RedisCacheService redisCacheService;


    @Override
    @ValidCheck
    public BasePageResponse<StakeMetaDataResponse> getStakePoolMetaData(BasePageRequest param) {
        Pageable pageable = param.makePageable();

        Long totalCount = stakePoolRepository.count();
        List<StakePool> stakeList = stakePoolRepository.getStakePoolData(param);

        List<StakeMetaDataResponse> result = stakeList
                .stream()
                .map(data -> {
                    Map<String, String> stakeToken = new HashMap<String, String>();
                    stakeToken.put(data.getStakeERC20().getContractName(), data.getStakeERC20().getContractAddress());

                    //TODO Reward Token, totalSupply는 데몬에서 RedisParsing해서 넣어줄 예정.
                    Map<String, String> rewardToken = redisCacheService.getValue(
                            DefineRedisCacheKey.getStakePoolRewardKey(data.getId()), Map.class);
                    BigDecimal totalSupply = redisCacheService.getValue(
                            DefineRedisCacheKey.getStakePoolTotalSupplyKey(data.getId()), BigDecimal.class);

                    return StakeMetaDataResponse.builder()
                            .stakePoolId(data.getId())
                            .contractName(data.getContract().getContractName())
                            .contractAddress(data.getContractAddress())
                            .ownerAddress(data.getContractAdmin().getPublicAddress())
                            .stakeToken(stakeToken)
                            .rewardToken(rewardToken)
                            .totalSupply(totalSupply)
                            .build();
                })
                .toList();
        return new BasePageResponse<>(result, pageable, totalCount);

    }
}
