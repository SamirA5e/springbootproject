package com.onlyjavatech.samir.service;

import com.onlyjavatech.samir.model.DepartmentModel.Department;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import com.onlyjavatech.samir.repository.ProjectRepository.ProjectRepository;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRepository projectRepository;

    public EmployeeResponseModel registerEmployee(EmployeeRequestModel request) {
        Employee employee = new Employee();
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

//        ===========================ManyToMany===============================
//          employee.getProjects()
//                .addAll(request
//                        .getProjects().
//                        stream().
//                        map(project -> {
//                        Project newProject = projectRepository.findById(project.getId()).get();
//                        newProject.getProjectName().add(employee);
//                        return newProject;
//                        }
//                    ).collect(Collectors.toList())
//                );

//        employee.setProjects(request.getProjects()
//                .stream()
//                .map(projectRow -> {
//                    Project newProject =projectService.getProjectByProjectName(projectRow.getProjectName());
//                    employeeRepository.save(newProject);
//                }));


//        ============================ End Of ManyToMany =====================
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        System.out.println("Your UUID is: " + uuidAsString);
        employee.setId(uuidAsString);

        Department department = departmentService.getDepartmentByDepartmentId(request.getDepartmentId());
        employee.setDepartment(department);
        Employee newEmployee = employeeRepository.save(employee);
        return setEmployeeResponseModel(newEmployee);
    }

    public EmployeeResponseModel updateEmployee(EmployeeRequestModel request) {
        String id = request.getId();
        Employee emp = employeeRepository.findById(id).get();
        emp.setFirstname(request.getFirstname());
        emp.setLastname(request.getLastname());
        emp.setEmailId(request.getEmailId());
        Employee updatedEmployee = employeeRepository.save(emp);
        return setEmployeeResponseModel(updatedEmployee);

    }

    //    public List<Employee> getEmployee() {
//        return (List<Employee>) employeeRepository.findAll();
//    }
    public List<EmployeeResponseModel> getEmployee() {
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseModel> employeeList = new ArrayList<>();

//        for (Employee employee : employees) {
////            EmployeeResponseModel employee =new EmployeeResponseModel();
//            EmployeeResponseModel employee_row = setEmployeeResponseModel(employee);
//
//            employeeList.add(employee_row);
//        }
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

        employeeRepository.deleteById(id);
    }

//    public EmployeeResponseModel deleteEmployee(EmployeeRequestModel request){
//        Integer id =request.getId();
//
//        EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
//        employeeRepository.deleteById(id);
//        return  employeeResponseModel;
//    }

    private EmployeeResponseModel setEmployeeResponseModel(Employee employee) {
        EmployeeResponseModel response = new EmployeeResponseModel();
        response.setId(employee.getId());
        response.setFirstname(employee.getFirstname());
        response.setLastname(employee.getLastname());
        response.setEmailId(employee.getEmailId());

        return response;
    }
}
