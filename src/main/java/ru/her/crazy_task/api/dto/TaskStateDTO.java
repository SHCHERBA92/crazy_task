package ru.her.crazy_task.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TaskStateDTO {
    private String name;
    private Integer orderTasks;
    @NonNull
    @JsonProperty("create_at")
    private Instant createAt;
    @JsonProperty("left_task_state_id")
    private Long leftTaskStateId;
    @JsonProperty("right_task_state_id")
    private Long rightTaskStateId;
    @NonNull
    List<TaskDTO> taskDTOS;
}
