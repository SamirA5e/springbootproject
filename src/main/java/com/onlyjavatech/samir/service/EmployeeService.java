package com.onlyjavatech.samir.service;

import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponseModel registerEmployee(EmployeeRequestModel request) {
        Employee employee = new Employee();
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        employee.setEmailId(request.getEmailId());

        Employee newEmployee = employeeRepository.save(employee);
        EmployeeResponseModel response = new EmployeeResponseModel();
        response.setId(newEmployee.getId());
        response.setFirstname(newEmployee.getFirstname());
        response.setLastname(newEmployee.getLastname());
        response.setEmailId(newEmployee.getEmailId());
        return response;
    }

    public EmployeeResponseModel updateEmployee(EmployeeRequestModel request) {
        Integer id = request.getId();
        Employee emp = employeeRepository.findById(id).get();
        emp.setFirstname(request.getFirstname());
        emp.setLastname(request.getLastname());
        emp.setEmailId(request.getEmailId());

         Employee emp1 = employeeRepository.save(emp);
         EmployeeResponseModel response = new EmployeeResponseModel();
         response.setId(emp1.getId());
         response.setFirstname(emp1.getFirstname());
         response.setLastname(emp1.getLastname());
         response.setEmailId(emp1.getEmailId());

         return response;

    }

//    public List<Employee> getEmployee() {
//        return (List<Employee>) employeeRepository.findAll();
//    }
    public List<EmployeeResponseModel> getEmployee(){
        Iterable<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseModel> employeeList = new ArrayList<>();

        for(Employee e:employees){
            EmployeeResponseModel employee =new EmployeeResponseModel();
            employee.setId(e.getId());
            employee.setFirstname(e.getFirstname());
            employee.setLastname(e.getLastname());
            employee.setEmailId(e.getEmailId());
            employeeList.add(employee);
        }

        return  employeeList;
    }




    public void deleteEmployee(Integer id) {

        employeeRepository.deleteById(id);
    }

//    public EmployeeResponseModel deleteEmployee(EmployeeRequestModel request){
//        Integer id =request.getId();
//
//        EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
//        employeeRepository.deleteById(id);
//        return  employeeResponseModel;
//    }
}
