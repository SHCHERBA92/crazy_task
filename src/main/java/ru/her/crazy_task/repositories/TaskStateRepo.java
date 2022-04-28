package ru.her.crazy_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.her.crazy_task.entitys.TaskStateEntity;

import java.util.List;

public interface TaskStateRepo extends JpaRepository<TaskStateEntity, Long> {

 }
