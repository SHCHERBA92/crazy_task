package ru.her.crazy_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.her.crazy_task.entitys.TaskEntity;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
