package com.onlyjavatech.samir.controller;

import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.service.EmployeeService;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//@Validated
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public EmployeeResponseModel registerEmployee(@Valid @RequestBody EmployeeRequestModel employee) {
        return employeeService.registerEmployee(employee);
    }

    @GetMapping
    public List<EmployeeResponseModel> getEmployees() {
        return employeeService.getEmployee();
    }

    @GetMapping("/{employee-id}")
    public EmployeeResponseModel getEmployeeById(@PathVariable(value = "employee-id") @NonNull String id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping()
    public EmployeeResponseModel updateEmployee(@RequestBody EmployeeRequestModel employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{employee-id}")
    public void deleteEmployee(@PathVariable(value = "employee-id") String id) {
        employeeService.deleteEmployee(id);
    }

}
