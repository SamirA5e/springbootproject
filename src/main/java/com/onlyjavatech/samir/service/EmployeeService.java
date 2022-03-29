package com.onlyjavatech.samir.service;

import com.onlyjavatech.samir.exception.ObjectNotFoundException;
import com.onlyjavatech.samir.model.DepartmentModel.Department;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @Transactional
    public EmployeeResponseModel registerEmployee(EmployeeRequestModel request) {
        Employee employee = new Employee();
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        System.out.println("Your UUID is: " + uuidAsString);
        employee.setId(uuidAsString);

        Department department = departmentService.getDepartmentByDepartmentId(request.getDepartmentId());
        employee.setDepartment(department);
        request.getProjects().forEach(project -> {
            Project newProject = new Project();
            String projectId = UUID.randomUUID().toString();
            newProject.setId(projectId);
            newProject.setProjectName(project.getProjectName());
            employee.addProject(newProject);

        });
        Employee newEmployee = employeeRepository.save(employee);
        return setEmployeeResponseModel(newEmployee);
    }

    public EmployeeResponseModel updateEmployee(EmployeeRequestModel request) {
        String id = request.getId();
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
//        Employee employee = null;
        if(!optionalEmployee.isPresent()){
            throw new ObjectNotFoundException("employee not found...",HttpStatus.NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

        request.getProjects().forEach(project -> {
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

        Employee updatedEmployee = employeeRepository.save(employee);
        return setEmployeeResponseModel(updatedEmployee);
    }

    public List<EmployeeResponseModel> getEmployee() {
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseModel> employeeList = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeResponseModel employee_row = setEmployeeResponseModel(employee);
            DepartmentResponseModel departmentResponse = new DepartmentResponseModel();
            departmentResponse.setId(employee.getDepartment().getId());
            departmentResponse.setDepartment_name((employee.getDepartment().getDepartment_name()));
            employee_row.setDepartment(departmentResponse);
            employeeList.add(employee_row);
        });
        return employeeList;
    }

    public EmployeeResponseModel getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id).get();

        return setEmployeeResponseModel(employee);
    }

    public void deleteEmployee(String id) {
        Project project = new Project();
        project.setId(id);
        Employee employee = new Employee();
        employee.removeProject(project);
        employeeRepository.deleteById(id);
    }

    private EmployeeResponseModel setEmployeeResponseModel(Employee employee) {
        EmployeeResponseModel response = new EmployeeResponseModel();
        response.setId(employee.getId());
        response.setFirstname(employee.getFirstname());
        response.setLastname(employee.getLastname());
        response.setEmailId(employee.getEmailId());

        return response;
    }
}
