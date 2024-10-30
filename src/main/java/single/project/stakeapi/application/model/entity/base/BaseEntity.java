package single.project.stakeapi.application.model.entity.base;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.LocalDateTime;

/**
 * author      : mark
 * date        : 2024/10/17
 * description : entity 공통 클래스
 **/

@Getter
@SuperBuilder
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

}
