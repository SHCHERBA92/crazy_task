package ru.her.crazy_task.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.her.crazy_task.api.controllers.helper.Helper;
import ru.her.crazy_task.api.dto.TaskStateDTO;
import ru.her.crazy_task.api.factories.MyFactory;
import ru.her.crazy_task.entitys.TaskStateEntity;
import ru.her.crazy_task.exeption.BadRequestException;
import ru.her.crazy_task.repositories.ProjectRepo;
import ru.her.crazy_task.repositories.TaskStateRepo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/project/")
public class TaskStateController {
    private final TaskStateRepo taskStateRepo;
    private final ProjectRepo projectRepo;
    private final MyFactory myFactory;
    private final Helper helper;

    @GetMapping("{project_id}/tasks/states")
    public List<TaskStateDTO> getAllTaskState(@PathVariable(name = "project_id") Long project_id){
        var project = helper.getProject(project_id);

        return project.getTaskStateEntities().stream()
                .map(myFactory::convertTaskStatToDTO)
                .collect(Collectors.toList());
    }
    @PostMapping("{project_id}/tasks/states")
    public TaskStateDTO createTaskState(@PathVariable(name = "project_id") Long project_id,
                                        @RequestParam(name = "taskStateName") String taskStateName){
        if (taskStateName.isBlank()) throw new BadRequestException("Не верные данные");
        var currentProject = helper.getProject(project_id);
        currentProject.getTaskStateEntities().stream()
                .map(TaskStateEntity::getName)
                .filter(nameOfTaskEntity -> taskStateName.equalsIgnoreCase(taskStateName))
                .findAny()
                .ifPresent(s -> {
                    throw new BadRequestException("Task state ready exist");
                });
        TaskStateEntity taskStateEntity = new TaskStateEntity();
        taskStateEntity.setName(taskStateName);
        taskStateRepo.saveAndFlush(taskStateEntity);
        return myFactory.convertTaskStatToDTO(taskStateEntity);
    }

}
