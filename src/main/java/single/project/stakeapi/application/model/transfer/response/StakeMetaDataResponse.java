package single.project.stakeapi.application.model.transfer.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import single.project.stakeapi.application.model.entity.StakePool;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StakeMetaDataResponse {
    Long stakePoolId;
    String contractName;
    String contractAddress;
    String ownerAddress;
    Map<String, String> stakeToken;
    Map<String, String> rewardToken;
    BigDecimal totalSupply;

}