package com.onlyjavatech.samir.service.ProjectService;

import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;
import com.onlyjavatech.samir.model.ProjectModel.ProjectResponseModel;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import com.onlyjavatech.samir.repository.ProjectRepository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ProjectResponseModel registerProject(ProjectRequestModel request){
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        System.out.println(request.getProjectName());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        project.setId(uuidAsString);
        Project newProject = projectRepository.save(project);

        return setProjectResponseModel(newProject);
    }



//    public ProjectResponseModel registerProjectWithEmployee(String employeeId,ProjectRequestModel projects){
//        System.out.println("---------- employee is by project service...----------");
//        employeeRepository.findById(employeeId).map(employee -> {
////          String projectName = projects.getProjectName();
//          Project projectResponse =projectRepository.save(projects);
//        })
//        return  null;
//    }
    public List<ProjectResponseModel> getProjects(){
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectResponseModel> projectList = new ArrayList<>();

        projects.forEach(projectRow->{
            ProjectResponseModel responseModel = setProjectResponseModel(projectRow);
            projectList.add(responseModel);
        });
        return projectList;
    }
    public ProjectResponseModel getProjectById(ProjectRequestModel projectRequest){
        return  null;
    }
//    public Project getProjectByProjectName(String name){
//
//        return null;
//    }

//    public EmployeeResponseModel registerEmployeeProject(ProjectRequestModel request)
//    {
//
//        return  null;
//    }
    public String consumeApi(){

        return null;
    }


    public String testingException(){
        System.out.println("Testing Exception.....");
        String str = null;
        System.out.println(str.length());
        return "home";
    }

    private ProjectResponseModel setProjectResponseModel(Project request)
    {
        ProjectResponseModel response = new ProjectResponseModel();
        response.setId(request.getId());
        response.setProjectName(request.getProjectName());
        return response;
    }
}
