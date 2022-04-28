package ru.her.crazy_task.api.controllers;

import org.springframework.web.bind.annotation.*;
import ru.her.crazy_task.api.dto.ActDTO;
import ru.her.crazy_task.api.dto.ProjectDTO;
import ru.her.crazy_task.api.factories.MyFactory;
import ru.her.crazy_task.entitys.ProjectEntity;
import ru.her.crazy_task.exeption.BadRequestException;
import ru.her.crazy_task.exeption.NotFoundException;
import ru.her.crazy_task.repositories.ProjectRepo;

import javax.websocket.server.PathParam;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class ProjectController {
    private final MyFactory myFactory;
    private ProjectRepo projectRepo;

    public ProjectController(MyFactory myFactory, ProjectRepo projectRepo) {
        this.myFactory = myFactory;
        this.projectRepo = projectRepo;
    }

    @GetMapping
    public List<ProjectDTO> getAllProject(@RequestParam(value = "prefix_name", required = false)Optional<String> optionalPrefixName){
        optionalPrefixName = optionalPrefixName.filter(prefixName->!prefixName.trim().isEmpty());
        List<ProjectEntity> entityStream = optionalPrefixName.isPresent()
                ? projectRepo.findAllByNameStartsWithIgnoreCase(optionalPrefixName.get())
                : projectRepo.findAll();
        return entityStream.stream()
                .map(myFactory::convertProjectEntityToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("projects")
    public ProjectDTO createProject(@RequestParam String name) {
        projectRepo.findByName(name).
                ifPresent(entity -> {
                    throw new BadRequestException("Такой проект уже существует");
                });
        ProjectEntity entity = new ProjectEntity();
        entity.setName(name);
        entity.setCreateAt(Instant.now());
        projectRepo.save(entity);
        ProjectDTO projectDTO = myFactory.convertProjectEntityToDTO(entity);
        return projectDTO;
    }

    @PutMapping("project/{project_id}")
    public ProjectDTO editeProject(@PathVariable("project_id") Long project_id,
                                    @PathParam("name") String name){
        ProjectEntity entity = projectRepo
                .findById(project_id)
                .orElseThrow(() -> new NotFoundException("нет такого проекта"));
        if (name.trim().isEmpty()) throw new BadRequestException("Bмя проекта не задано");
        projectRepo.findByName(name)
                .ifPresent(entity1 -> new BadRequestException("такой проект уже существуе"));
        entity.setName(name);
        projectRepo.save(entity);
        return myFactory.convertProjectEntityToDTO(entity);
    }


    @DeleteMapping("project/delete/{project_name}")
    public ActDTO deleteProject(@PathVariable("project_name") String project_name){
        projectRepo.deleteById(
                projectRepo
                .findByName(project_name)
                .orElseThrow(() -> new NotFoundException("такого проекта нет")).getId()
        );
        return ActDTO.makeDefault(true);
    }
}
