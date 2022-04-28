package ru.her.crazy_task.api.factories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.her.crazy_task.api.dto.ProjectDTO;
import ru.her.crazy_task.api.dto.TaskDTO;
import ru.her.crazy_task.api.dto.TaskStateDTO;
import ru.her.crazy_task.entitys.ProjectEntity;
import ru.her.crazy_task.entitys.TaskEntity;
import ru.her.crazy_task.entitys.TaskStateEntity;

import java.time.Instant;

@Component
public class MyFactory {
    private final ModelMapper modelMapper;

    public MyFactory() {
        this.modelMapper = new ModelMapper();
    }

    public ProjectDTO convertProjectEntityToDTO(ProjectEntity entity) {
        var projectDTO = modelMapper.map(entity, ProjectDTO.class);
        return projectDTO;
    }

    public ProjectEntity convertProjectDTOtoEntity(ProjectDTO projectDTO) {
        ProjectEntity pr = modelMapper.map(projectDTO, ProjectEntity.class);
        pr.setCreateAt(Instant.now());
        return pr;
    }

    //////////////

    public TaskStateDTO convertTaskStatToDTO(TaskStateEntity entity) {
        return modelMapper.map(entity, TaskStateDTO.class);
    }

    public TaskStateEntity convertTaskStateDTOtoEntity(TaskStateDTO taskStateDTO) {
        TaskStateEntity pr = modelMapper.map(taskStateDTO, TaskStateEntity.class);
        pr.setCreateAt(Instant.now());
        return pr;
    }

    //////////////

    public TaskDTO convertTaskToDTO(TaskEntity entity) {
        return modelMapper.map(entity, TaskDTO.class);
    }

    public TaskEntity convertTaskDTOtoEntity(TaskDTO taskDTO) {
        TaskEntity pr = modelMapper.map(taskDTO, TaskEntity.class);
        pr.setCreateAdd(Instant.now());
        return pr;
    }

}
