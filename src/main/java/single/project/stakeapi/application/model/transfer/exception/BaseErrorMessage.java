package single.project.stakeapi.application.model.transfer.exception;

import org.springframework.http.HttpStatus;


public interface BaseErrorMessage {
    Integer getCode();
    String getMessage();
    HttpStatus getStatus();
}
