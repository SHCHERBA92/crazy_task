package ru.her.crazy_task.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_state_")
public class TaskStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToOne
    private TaskStateEntity leftTaskState;
    @OneToOne
    private TaskStateEntity rightTaskState;
    private Instant createAt = Instant.now();

    @OneToMany
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private List<TaskEntity> taskEntities;
}
