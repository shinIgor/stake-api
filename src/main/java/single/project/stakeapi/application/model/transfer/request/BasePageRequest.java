package single.project.stakeapi.application.model.transfer.request;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasePageRequest {
    @NotNull
    Integer page = 0;

    @NotNull
    Integer pageSize = 10;
    public Pageable makePageable() {
        return PageRequest.of(page, pageSize);
    }
    public Pageable makePageable(Sort sort) {
        return PageRequest.of(page, pageSize, sort);
    }
}
