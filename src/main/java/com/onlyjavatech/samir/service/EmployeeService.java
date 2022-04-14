package com.onlyjavatech.samir.service;

import com.onlyjavatech.samir.exception.ObjectNotFoundException;
import com.onlyjavatech.samir.exception.ValidationHandler;
import com.onlyjavatech.samir.model.DepartmentModel.Department;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import com.onlyjavatech.samir.model.search_model.SearchRequestModel;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

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

    public void removeProjectFromProjectsList(Project project) {
        Employee employee = new Employee();
        employee.removeProject(project);
        employeeRepository.save(employee);

    }

    public Employee checkEmployeePresentOrNot(String id) {
        if (id == null || id.isEmpty()) {
            throw new ValidationHandler("Employee Id can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new ObjectNotFoundException("employee not found...", HttpStatus.NOT_FOUND);
        }
        return optionalEmployee.get();
    }

    public EmployeeResponseModel saveEmployeeForProjectEmployeeService(Employee employee) {
        return setEmployeeResponseModel(employeeRepository.save(employee));
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
            throw new EntityNotFoundException("Employee not found..");
        }
        Employee employee = optionalEmployee.get();
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
            throw new ValidationHandler("Employee not found ,please provide valid Employee Id..", HttpStatus.BAD_REQUEST);
        }
        employeeRepository.deleteById(id);
    }

    public void removeProjectsRelation(Project project) {
        List<Employee> employeeList = employeeRepository.findByProjects_Id(project.getId());
        employeeList.forEach(employee -> {
            employee.removeProject(project);
            employeeRepository.save(employee);
        });
    }

    public List<EmployeeResponseModel> getEmployeeByDepartmentId(String id) {
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        employeeRepository.getEmployeeByDepartmentId(id).forEach(employee -> {
            employeeResponseModelList.add(setEmployeeResponseModel(employee));
        });
        return employeeResponseModelList;
    }

    public List<EmployeeResponseModel> getEmployeesByProjectId(String id) {
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        employeeRepository.findByProjects_Id(id).forEach(employee -> {
            employeeResponseModelList.add(setEmployeeResponseModel(employee));
        });
        return employeeResponseModelList;
    }

    public List<EmployeeResponseModel> getEmployeesByProjectIdByPagination(String id, Integer pageNo, Integer pageSize) {
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        if (pageNo == null || pageSize == null) {
            employeeRepository.getEmployeesByProjectId(id).forEach(employee -> {
                employeeResponseModelList.add(setEmployeeResponseModel(employee));
            });
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            employeeRepository.getEmployeesByProjectIdByPagination(id, pageable).forEach(employee -> {
                employeeResponseModelList.add(setEmployeeResponseModel(employee));
            });
        }

        return employeeResponseModelList;
    }

    public List<EmployeeResponseModel> getEmployeesBySearch(SearchRequestModel request, Integer pageNo, Integer pageSize) {

        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();

        if (pageNo == null || pageSize == null) {
            employeeRepository.findByEmailIdOrFirstnameOrLastnameOrDepartment_DepartmentNameOrderByFirstnameAsc(request.getEmailId(), request.getFirstName(), request.getLastName(), request.getDepartmentName()).forEach(employee -> {
                employeeResponseModelList.add(setEmployeeResponseModel(employee));
            });
        } else {

            Pageable pageable = PageRequest.of(pageNo, pageSize);
            employeeRepository.findByEmailIdOrFirstnameOrLastnameOrDepartment_DepartmentNameOrderByFirstnameAsc(request.getEmailId(), request.getFirstName(), request.getLastName(), request.getDepartmentName(), pageable).forEach(employee -> {
                employeeResponseModelList.add(setEmployeeResponseModel(employee));
            });
        }

        return employeeResponseModelList;
    }

    public List<EmployeeResponseModel> getAllEmployeesByQueryByPageNoAndPageSize(Integer pageNo, Integer pageSize) {
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        if (pageNo == null || pageSize == null) {
            employeeRepository.findAll().forEach(employee -> {
                employeeResponseModelList.add(setEmployeeResponseModel(employee));
            });
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);

            employeeRepository.getAllEmployeesByPagination(pageable).forEach(employee -> {
                employeeResponseModelList.add(setEmployeeResponseModel(employee));
            });
        }

        return employeeResponseModelList;
    }

    public List<EmployeeResponseModel> getAllEmployeesByQuery() {
        List<Employee> employeeList = employeeRepository.getAllEmployeesByQuery();
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        employeeList.forEach(employee -> {
            employeeResponseModelList.add(setEmployeeResponseModel(employee));
        });
        return employeeResponseModelList;
    }

    public List<EmployeeResponseModel> getAllEmployeesByNativeQuery() {
        List<Employee> employeeList = employeeRepository.getAllEmployeesByNativeQuery();
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        employeeList.forEach(employee -> {
            employeeResponseModelList.add(setEmployeeResponseModel(employee));
        });
        return employeeResponseModelList;
    }

    public EmployeeResponseModel getAllEmployeesByQueryId(String id) {
        Employee employee = employeeRepository.getAllEmployeesByQueryId(id);

        return setEmployeeResponseModel(employee);
    }

    public EmployeeResponseModel getAllEmployeesByNativeQueryById(String id) {
        Employee employee = employeeRepository.getAllEmployeesByNativeQueryId(id);

        return setEmployeeResponseModel(employee);
    }

    public Page<Employee> getAllEmployeesByPaginationById(int page, String id) {
        Pageable pageable = PageRequest.of(page, 1);
        return employeeRepository.getAllEmployeesByPaginationById(id, pageable);
    }

    public List<EmployeeResponseModel> getAllEmployeesByPagination(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        List<EmployeeResponseModel> employeeResponseModelList = new ArrayList<>();
        employeeRepository.getAllEmployeesByPagination(pageable).forEach(employee -> {
            employeeResponseModelList.add(setEmployeeResponseModel(employee));
        });
        return employeeResponseModelList;
    }

    private EmployeeResponseModel setEmployeeResponseModel(Employee employee) {
        EmployeeResponseModel response = new EmployeeResponseModel();
        response.setId(employee.getId());
        response.setFirstname(employee.getFirstname());
        response.setLastname(employee.getLastname());
        response.setEmailId(employee.getEmailId());
        response.setDepartment(departmentService.setDepartResponseModel(employee.getDepartment()));
        return response;
    }


}
