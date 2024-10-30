package single.project.stakeapi.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import single.project.stakeapi.application.model.transfer.request.BasePageRequest;
import single.project.stakeapi.application.model.transfer.response.BasePageResponse;
import single.project.stakeapi.application.model.transfer.response.BaseResponse;
import single.project.stakeapi.application.service.StakeMetaDataService;

@Slf4j
@RestController
@RequestMapping("stake-api/v1/metadata")
@RequiredArgsConstructor
public class StakeMetaDataController {
    private final StakeMetaDataService stakeMetaDataService;

    @GetMapping()
    public BaseResponse<BasePageResponse<?>> getStakeMetaData(BasePageRequest param) {
        stakeMetaDataService.getStakePoolMetaData(param);
        return BaseResponse.success();
    }


}
