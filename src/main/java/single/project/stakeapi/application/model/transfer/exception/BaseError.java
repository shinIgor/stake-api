package single.project.stakeapi.application.model.transfer.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseError {
    Integer code;
    String message;

    public BaseError(BaseErrorMessage type) {
        this.code = type.getCode();
        this.message = type.getMessage();
    }
}
