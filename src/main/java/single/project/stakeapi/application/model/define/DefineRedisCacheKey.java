package single.project.stakeapi.application.model.define;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DefineRedisCacheKey {
    private final String REDIS_KEY_DELIMITER = ":";
    private final String SERVICE_NAME = "stake-api";

    public String getStakePoolRewardKey(Long stakePoolId) {
        return String.join(REDIS_KEY_DELIMITER, SERVICE_NAME,
                "rewardToken", stakePoolId.toString());
    }

    public String getStakePoolTotalSupplyKey(Long stakePoolId) {
        return String.join(REDIS_KEY_DELIMITER, SERVICE_NAME,
                "totalSupply", stakePoolId.toString());
    }
}

