package single.project.stakeapi.application.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import single.project.stakeapi.application.model.transfer.exception.BaseError;
import single.project.stakeapi.application.model.transfer.exception.CommonErrorMessage;
import single.project.stakeapi.application.model.transfer.response.BaseResponse;

import java.util.Optional;

@Hidden
@Slf4j
@Controller
public class BaseErrorController {

    @GetMapping("/error")
    @ResponseBody
    public BaseResponse<?> handleError(HttpServletRequest request,
                                       @RequestParam(name = "code", required = false) Integer code) {
        HttpStatus status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .map(e -> HttpStatus.valueOf(Integer.parseInt(e.toString())))
                .orElseGet(()->HttpStatus.valueOf(code));
        return getErrorPage(status);
    }

    private BaseResponse<?> getErrorPage(HttpStatus status) {
        if (HttpStatus.NOT_FOUND.equals(status))
            return BaseResponse.of(false, new BaseError(CommonErrorMessage.NOT_FOUND), null);

        if (HttpStatus.BAD_REQUEST.equals(status))
            return BaseResponse.of(false, new BaseError(CommonErrorMessage.BAD_REQUEST), null);

        if (HttpStatus.FORBIDDEN.equals(status))
            return BaseResponse.of(false, new BaseError(CommonErrorMessage.FORBIDDEN), null);

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
            return BaseResponse.of(false, new BaseError(CommonErrorMessage.INTERNAL_SERVER_ERROR), null);

        if (HttpStatus.UNAUTHORIZED.equals(status))
            return BaseResponse.of(false, new BaseError(CommonErrorMessage.UNAUTHORIZED), null);

        return BaseResponse.of(false, new BaseError(CommonErrorMessage.UNKNOWN), null);
    }
}
