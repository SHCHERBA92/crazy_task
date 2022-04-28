package ru.her.crazy_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.her.crazy_task.entitys.ProjectEntity;
import ru.her.crazy_task.entitys.TaskStateEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByName(String name);

    List<ProjectEntity> findAllByNameStartsWithIgnoreCase(String prefixName);

    List<TaskStateEntity> findAllById(Long id);
}
