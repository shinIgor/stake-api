package single.project.stakeapi.application.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PageUtil {
    public static Long getTotalPage(Integer pageSize, Long totalElementCount) {
        if (totalElementCount < pageSize.longValue()) {
            return 1L;
        }

        long totalPageCount = totalElementCount / pageSize.longValue();

        if (0 != (totalElementCount % pageSize.longValue())) {
            totalPageCount += 1;
        }

        return totalPageCount;
    }

}
