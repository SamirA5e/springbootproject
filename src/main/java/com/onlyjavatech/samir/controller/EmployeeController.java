package com.onlyjavatech.samir.controller;

import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseModel registerEmployee(@RequestBody EmployeeRequestModel employee) {
        return employeeService.registerEmployee(employee);
    }

//    @GetMapping("/getEmployee")
//    public List<Employee> getEmployee(){
//        return  employeeService.getEmployee();
//    }

    //    @PutMapping("/updateEmployee")
//    public Employee updateEmployee(@RequestBody Employee employee){
//        return employeeService.updateEmployee(employee);
//    }
    @GetMapping
    public List<EmployeeResponseModel> getEmployees() {
        return employeeService.getEmployee();
    }
    @GetMapping("/{employee-id}")
    public EmployeeResponseModel getEmployeeById(@PathVariable(value = "employee-id") String id)
    {
        return  employeeService.getEmployeeById(id);
    }

    @PutMapping()
    public EmployeeResponseModel updateEmployee(@RequestBody EmployeeRequestModel employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{employee-id}")
    public void deleteEmployee(@PathVariable(value = "employee-id") String id) {
        employeeService.deleteEmployee(id);
    }

//    @DeleteMapping("/deleteEmployee")
//    public EmployeeResponseModel deleteEmployee(@RequestBody EmployeeRequestModel employee)
//    {
//        return employeeService.deleteEmployee(employee);
//    }

}
