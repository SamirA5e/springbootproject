package com.onlyjavatech.samir.service.ProjectService;

import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;
import com.onlyjavatech.samir.model.ProjectModel.ProjectResponseModel;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import com.onlyjavatech.samir.repository.ProjectRepository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ProjectResponseModel registerProject(ProjectRequestModel request) {
        Project project = new Project();
        project.setProjectName(request.getProjectName());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        project.setId(uuidAsString);
        Project newProject = projectRepository.save(project);

        return setProjectResponseModel(newProject);
    }

    public List<ProjectResponseModel> getProjects() {
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectResponseModel> projectList = new ArrayList<>();

        projects.forEach(projectRow -> {
            ProjectResponseModel responseModel = setProjectResponseModel(projectRow);
            projectList.add(responseModel);
        });
        return projectList;
    }

    public ProjectResponseModel getProjectById(ProjectRequestModel projectRequest) {
        return null;
    }

    public String consumeApi() {

        return null;
    }


    public String testingException() {
        String str = null;
        System.out.println(str.length());
        return "home";
    }

    public Boolean checkProjectByProjectId(String id) {
        return projectRepository.existsById(id);
    }

    public Project getProjectByProjectId(String id) {
        return projectRepository.findById(id).get();
    }


    private ProjectResponseModel setProjectResponseModel(Project request) {
        ProjectResponseModel response = new ProjectResponseModel();
        response.setId(request.getId());
        response.setProjectName(request.getProjectName());
        return response;
    }
}
