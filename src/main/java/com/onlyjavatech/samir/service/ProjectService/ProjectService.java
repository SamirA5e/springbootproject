package com.onlyjavatech.samir.service.ProjectService;

import com.onlyjavatech.samir.exception.ObjectNotFoundException;
import com.onlyjavatech.samir.exception.ValidationHandler;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;
import com.onlyjavatech.samir.model.ProjectModel.ProjectResponseModel;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import com.onlyjavatech.samir.repository.ProjectRepository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ProjectResponseModel registerProject(ProjectRequestModel request) {
        if (request == null) {
            throw new ObjectNotFoundException("Project request can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        if (request.getProjectName() == null || request.getProjectName().isEmpty()) {
            throw new ValidationHandler("Project name can't be null or empty..", HttpStatus.BAD_REQUEST);
        }
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

    public ProjectResponseModel getProjectById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ValidationHandler("Project Id can't be null or empty..", HttpStatus.BAD_REQUEST);
        }
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            throw new ValidationHandler("Provided project Id is not present in our database please provide valid project Id...", HttpStatus.BAD_REQUEST);
        }
        return setProjectResponseModel(optionalProject.get());
    }

    public Boolean checkProjectByProjectId(String id) {
        return projectRepository.existsById(id);
    }

    public Project getProjectByProjectId(String id) {
        return projectRepository.findById(id).get();
    }

    public ProjectResponseModel updateProject(ProjectRequestModel request){
        if(request==null){
            throw new ObjectNotFoundException("Project request can't be null or empty...",HttpStatus.BAD_REQUEST);
        }
        if(request.getId()==null || request.getId().isEmpty()){
            throw new ValidationHandler("Project Id can't be null or empty...",HttpStatus.BAD_REQUEST);
        }
        if(request.getProjectName()==null || request.getProjectName().isEmpty()){
            throw new ValidationHandler("Project name can't be null or empty...",HttpStatus.BAD_REQUEST);
        }
        Optional<Project> optionalProject = projectRepository.findById(request.getId());
        if(!optionalProject.isPresent()){
            throw new ValidationHandler("Project id is not present in our database, please provide valid project id...",HttpStatus.BAD_REQUEST);
        }
        Project project = optionalProject.get();
        project.setProjectName(request.getProjectName());
        return setProjectResponseModel(projectRepository.save(project));
    }

    private ProjectResponseModel setProjectResponseModel(Project request) {
        ProjectResponseModel response = new ProjectResponseModel();
        response.setId(request.getId());
        response.setProjectName(request.getProjectName());
        return response;
    }
}
