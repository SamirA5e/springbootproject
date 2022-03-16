package com.onlyjavatech.samir.service.ProjectService;

import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;
import com.onlyjavatech.samir.model.ProjectModel.ProjectResponseModel;
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
    public List<ProjectResponseModel> getProjects(){
        Iterable<Project> projects = projectRepository.findAll();
        List<ProjectResponseModel> projectList = new ArrayList<>();

        projects.forEach(projectRow->{
            ProjectResponseModel responseModel = setProjectResponseModel(projectRow);
            projectList.add(responseModel);
        });
        return projectList;
    }

    private ProjectResponseModel setProjectResponseModel(Project request)
    {
        ProjectResponseModel response = new ProjectResponseModel();
        response.setId(request.getId());
        response.setProjectName(request.getProjectName());
        return response;
    }
}
