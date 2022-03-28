package com.onlyjavatech.samir.controller.Projects;

import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;
import com.onlyjavatech.samir.model.ProjectModel.ProjectResponseModel;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ProjectResponseModel registerProject(@RequestBody ProjectRequestModel request) {
        return projectService.registerProject(request);
    }

    @ResponseBody
    @GetMapping("/getTestApi")
    public String getTestApi() {
        return projectService.consumeApi();
    }

    @GetMapping("/testingException")
    public String testingException() {
        return projectService.testingException();
    }

    @GetMapping
    public List<ProjectResponseModel> getProjects() {
        return projectService.getProjects();
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
