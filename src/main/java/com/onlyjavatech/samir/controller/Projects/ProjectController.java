package com.onlyjavatech.samir.controller.Projects;

import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;
import com.onlyjavatech.samir.model.ProjectModel.ProjectResponseModel;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Project Controller", description = "This controller performs operations on project table")
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Operation(summary = "This Api store project in project table", description = "This Api accepts project request and store it to the project tables")
    @ApiResponse(responseCode = "200", description = "Project Store successfully to database")
    @PostMapping
    public ProjectResponseModel registerProject(@RequestBody ProjectRequestModel request) {
        return projectService.registerProject(request);
    }

    @Operation(summary = "This Api fetch all projects", description = "This Api fetch all projects from projects table")
    @ApiResponse(responseCode = "200", description = "Projects Fetch successfully")
    @GetMapping
    public List<ProjectResponseModel> getProjects() {
        return projectService.getProjects();
    }

    @Operation(summary = "This Api fetch particular project", description = "This api fetch particular project based on provided project id")
    @ApiResponse(responseCode = "200", description = "Project Fetch Successfully")
    @GetMapping("/{project-id}")
    public ProjectResponseModel getDepartmentById(@PathVariable(value = "project-id") String id) {
        return projectService.getProjectById(id);
    }

    @Operation(summary = "This Api update projects", description = "This Api update projects to the projects table based on the provided project id in request")
    @ApiResponse(responseCode = "200", description = "Project updated successfully")
    @PutMapping()
    public ProjectResponseModel updateProject(@RequestBody ProjectRequestModel request) {
        return projectService.updateProject(request);
    }

    @Operation(summary = "This api remove particular project from projects table")
    @ApiResponse(responseCode = "200", description = "Project removed successfully")
    @DeleteMapping("/{project-id}")
    public void removeProject(@PathVariable(value = "project-id") String id) {
        projectService.removeProject(id);
    }
//    ---------------    Without using @ControllerAdvice  ----------------
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler({NullPointerException.class,NumberFormatException.class,ArrayIndexOutOfBoundsException.class})
//    public String exceptionHandlerNull(){
//        return "nullPage";
//    }
//
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = Exception.class)
//    public String exceptionHandlerGeneric(){
//        return "nullPage";
//    }

}
