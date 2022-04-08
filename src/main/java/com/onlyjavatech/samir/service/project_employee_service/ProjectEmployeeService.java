package com.onlyjavatech.samir.service.project_employee_service;

import com.onlyjavatech.samir.exception.ObjectNotFoundException;
import com.onlyjavatech.samir.exception.ValidationHandler;
import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.service.EmployeeService;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProjectEmployeeService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    public EmployeeResponseModel updateEmployee(EmployeeRequestModel request) {
        if (request == null) {
            throw new ObjectNotFoundException("Object can't be null...", HttpStatus.NOT_FOUND);
        }
        if (request.getId() == null || request.getId().isEmpty()) {
            throw new ValidationHandler("Employee Id can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        if (request.getFirstname() == null || request.getFirstname().isEmpty()) {
            throw new ValidationHandler("Employee First name can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        if (request.getLastname() == null || request.getLastname().isEmpty()) {
            throw new ValidationHandler("Employee Last name can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        if (request.getEmailId() == null || request.getEmailId().isEmpty()) {
            throw new ValidationHandler("Employee Email Id can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        if (request.getProjects() == null || request.getProjects().isEmpty()) {
            throw new ValidationHandler("Projects list can't be null or empty", HttpStatus.BAD_REQUEST);
        }
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(request.getEmailId());
        if (!matcher.matches()) {
            throw new ValidationHandler("Please provide valid email like abc@gmail.com", HttpStatus.BAD_REQUEST);
        }

        String id = request.getId();
        Employee employee = employeeService.checkEmployeePresentOrNot(id);

        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

        request.getProjects().forEach(project -> {
            if (project == null) {
                throw new ObjectNotFoundException("Project can't be null or empty", HttpStatus.BAD_REQUEST);
            }
            if (project.getId() == null || project.getId().isEmpty()) {
                throw new ValidationHandler("Project Id can't be Empty or null", HttpStatus.BAD_REQUEST);
            }
            if (project.getProjectName() == null || project.getProjectName().isEmpty()) {
                throw new ValidationHandler("Project Name can't be null or empty", HttpStatus.BAD_REQUEST);
            }
            if (!projectService.checkProjectByProjectId(project.getId())) {
                Project newProject = new Project();
                String projectId = UUID.randomUUID().toString();
                newProject.setId(projectId);
                newProject.setProjectName(project.getProjectName());
                employee.addProject(newProject);
            } else {
                Project newProjectElse = projectService.getProjectByProjectId(project.getId());
                employee.addProject(newProjectElse);
            }
        });

        request.getRemoveProjects().forEach(project -> {
            if (!projectService.checkProjectByProjectId(project.getId())) {
                throw new EntityNotFoundException();
            } else {
                Project newProjectElse = projectService.getProjectByProjectId(project.getId());
                employee.removeProject(newProjectElse);
            }
        });
        return employeeService.saveEmployeeForProjectEmployeeService(employee);
    }
}
