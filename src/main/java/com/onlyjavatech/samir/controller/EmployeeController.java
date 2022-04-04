package com.onlyjavatech.samir.controller;

import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.service.EmployeeService;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller",description = "This controller provide Api to fetch and store data in employee table and join tables")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "This api will Store employee in database",description = "This api accept Json request and store this request to database")
    @ApiResponse(responseCode = "200",description = "Employee Stored in database")
    @PostMapping
    public EmployeeResponseModel registerEmployee(@Valid @RequestBody EmployeeRequestModel employee) {
        return employeeService.registerEmployee(employee);
    }

    @Operation(summary = "This api will fetch all employees", description = "this is description for api")
    @ApiResponse(responseCode = "200", description = "Fetch all the employees from database")
    @GetMapping
    public List<EmployeeResponseModel> getEmployees() {
        return employeeService.getEmployee();
    }

    @Operation(summary = "This api provide employee by employee id", description = "This is description")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "This api will fetch employee from database whose employee id matches with id in database", content = @Content(mediaType = "application/json"))})
    @GetMapping("/{employee-id}")
    public EmployeeResponseModel getEmployeeById(@PathVariable(value = "employee-id") @NonNull String id) {
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "This Api update data in employee table...",description = "this api accepts request with employee id ,using that id we update data in employee table")
    @ApiResponse(responseCode = "200",description = "Employee data updated in database")
    @PutMapping()
    public EmployeeResponseModel updateEmployee(@RequestBody EmployeeRequestModel employee) {
        return employeeService.updateEmployee(employee);
    }

    @Operation(summary = "This Api Remove Employee from database",description = "This api accepts employee id and remove that employee from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Employee removed successfully"),@ApiResponse(responseCode = "500",description = "Internal Server Error or Employee not found")})
    @DeleteMapping("/{employee-id}")
    public void deleteEmployee(@PathVariable(value = "employee-id") String id) {
        employeeService.deleteEmployee(id);
    }

}
