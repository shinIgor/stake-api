package single.project.stakeapi.application.model.transfer.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BasePageResponse<T> {
    Integer currentPage = 0;
    Integer pageSize = 0;
    Long totalPage = 0L;
    List<T> contents = new ArrayList<>();
}

