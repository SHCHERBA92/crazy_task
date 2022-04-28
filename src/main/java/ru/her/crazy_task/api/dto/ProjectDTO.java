package ru.her.crazy_task.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.her.crazy_task.entitys.TaskStateEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProjectDTO {
    private String name;

    @JsonProperty("create_at")
    private Instant createAt;
}
