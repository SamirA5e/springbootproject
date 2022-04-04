package com.onlyjavatech.samir.service;

import com.onlyjavatech.samir.exception.ObjectNotFoundException;
import com.onlyjavatech.samir.exception.ValidationHandler;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Validated
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
        if (request == null) {
            throw new ObjectNotFoundException("Object not found", HttpStatus.BAD_REQUEST);
        }
        if (request.getFirstname() == null || request.getFirstname().isEmpty()) {
            throw new ValidationHandler("Please Provide Valid First Name", HttpStatus.BAD_REQUEST);
        }
        if (request.getLastname() == null || request.getLastname().isEmpty()) {
            throw new ValidationHandler("Please Provide Valid Last Name", HttpStatus.BAD_REQUEST);
        }
        if (request.getEmailId() == null || request.getEmailId().isEmpty()) {
            throw new ValidationHandler("Please Provide Valid EmailId", HttpStatus.BAD_REQUEST);
        }
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(request.getEmailId()).matches()) {
            System.out.println("---- email validation -----");
            throw new ValidationHandler("Please Provide Valid EmailId like abc@gmail.com", HttpStatus.BAD_REQUEST);
        }

        if (request.getDepartmentId() == null || request.getDepartmentId().isEmpty()) {
            throw new ValidationHandler("Please Provide Valid DepartmentId", HttpStatus.BAD_REQUEST);
        }
        if (request.getProjects().isEmpty()) {
            throw new ValidationHandler("Please Provide ProjectList", HttpStatus.BAD_REQUEST);
        }

        Employee employee = new Employee();
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        employee.setId(uuidAsString);

        Department department = departmentService.getDepartmentByDepartmentId(request.getDepartmentId());
        employee.setDepartment(department);
        request.getProjects().forEach(project -> {
            if (project == null) {
                throw new ValidationHandler("Please Provide Valid ProjectList objects", HttpStatus.BAD_REQUEST);
            }
            if (project.getProjectName() == null || project.getProjectName().isEmpty()) {
                throw new ValidationHandler("Please Provide Valid ProjectList Name", HttpStatus.BAD_REQUEST);
            }
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
//
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(request.getEmailId());
        if (!matcher.matches()) {
            throw new ValidationHandler("Please provide valid email like abc@gmail.com", HttpStatus.BAD_REQUEST);
        }

        String id = request.getId();
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new ObjectNotFoundException("employee not found...", HttpStatus.NOT_FOUND);
        }
        Employee employee = optionalEmployee.get();
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
        if (id == null || id.isEmpty() || id.trim().isEmpty()) {
            throw new ValidationHandler("Employee is can't be null or empty", HttpStatus.BAD_REQUEST);
        }
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new EntityNotFoundException();
        }
        Employee employee = optionalEmployee.get();
        System.out.println(employee.getDepartment().getDepartment_name() + "---");
        EmployeeResponseModel employeeResponseModel = setEmployeeResponseModel(employee);
        employeeResponseModel.setDepartment(departmentService.getDepartmentById(employee.getDepartment().getId()));
        return employeeResponseModel;
    }

    public void deleteEmployee(String id) {
        if (id == null || id.isEmpty() || id.trim().isEmpty()) {
            throw new ValidationHandler("Employee is can't be null or empty", HttpStatus.BAD_REQUEST);
        }
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new ValidationHandler("Employee not found ,please provide valid Employee Id..",HttpStatus.BAD_REQUEST);
        }
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
