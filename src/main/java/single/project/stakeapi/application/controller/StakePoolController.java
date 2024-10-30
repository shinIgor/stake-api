package single.project.stakeapi.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import single.project.stakeapi.application.model.transfer.response.BasePageResponse;
import single.project.stakeapi.application.model.transfer.response.BaseResponse;

@Tag(name = "[StakePool] 데이터 제공")
@RestController
@RequiredArgsConstructor
@RequestMapping("stake/v1/metadata")
public class StakePoolController {
    @GetMapping("")
    public BaseResponse<BasePageResponse<?>> getStakeMetaData() {
        return BaseResponse.success();
    }
}
