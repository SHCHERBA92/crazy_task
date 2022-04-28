package ru.her.crazy_task.api.controllers.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.her.crazy_task.entitys.ProjectEntity;
import ru.her.crazy_task.exeption.NotFoundException;
import ru.her.crazy_task.repositories.ProjectRepo;

@Component
@Transactional
@RequiredArgsConstructor
public class Helper {

    private final ProjectRepo projectRepo;

    public ProjectEntity getProject(Long id){
        return projectRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("vvv"));
    }
}
