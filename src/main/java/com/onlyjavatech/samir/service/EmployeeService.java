package com.onlyjavatech.samir.service;
import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponseModel registerEmployee(EmployeeRequestModel request) {
        Employee employee = new Employee();
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        System.out.println("Your UUID is: " + uuidAsString);
        employee.setId(uuidAsString);

        Employee newEmployee = employeeRepository.save(employee);
//        EmployeeResponseModel response = new EmployeeResponseModel();
//        response.setId(newEmployee.getId());
//        response.setFirstname(newEmployee.getFirstname());
//        response.setLastname(newEmployee.getLastname());
//        response.setEmailId(newEmployee.getEmailId());
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

        for (Employee employee : employees) {
//            EmployeeResponseModel employee =new EmployeeResponseModel();
            EmployeeResponseModel employee_row = setEmployeeResponseModel(employee);

            employeeList.add(employee_row);
        }

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
