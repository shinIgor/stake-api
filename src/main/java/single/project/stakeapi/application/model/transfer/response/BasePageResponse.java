package single.project.stakeapi.application.model.transfer.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import single.project.stakeapi.application.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BasePageResponse<T> {
    Integer currentPage = 0;
    Integer pageSize = 0;
    Integer totalPage = 0;
    Long totalElements = 0L;
    List<T> contents = new ArrayList<>();

    public BasePageResponse(List<T> contents, Pageable pageable, Long count) {
        this.currentPage = pageable.getPageNumber() + 1;
        this.pageSize = pageable.getPageSize();
        this.totalElements = count;
        this.totalPage = (Math.toIntExact(PageUtil.getTotalPage(pageable.getPageSize(), totalElements)));
        this.contents = contents;
    }
}

