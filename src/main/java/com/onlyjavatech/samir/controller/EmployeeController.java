package com.onlyjavatech.samir.controller;

import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.EmployeeRequestModel;
import com.onlyjavatech.samir.model.EmployeeResponseModel;
import com.onlyjavatech.samir.service.EmployeeService;
import com.onlyjavatech.samir.service.ProjectService.ProjectService;
import com.onlyjavatech.samir.service.project_employee_service.ProjectEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Controller", description = "This controller provide Api to fetch and store data in employee table and join tables")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectEmployeeService projectEmployeeService;

    @Operation(summary = "This api will Store employee in database", description = "This api accept Json request and store this request to database")
    @ApiResponse(responseCode = "200", description = "Employee Stored in database")
    @PostMapping
    public EmployeeResponseModel registerEmployee(@RequestBody EmployeeRequestModel employee) {
        return employeeService.registerEmployee(employee);
    }

//    @Operation(summary = "This api will fetch all employees", description = "this is description for api")
//    @ApiResponse(responseCode = "200", description = "Fetch all the employees from database")
//    @GetMapping
//    public List<EmployeeResponseModel> getEmployees() {
//        return employeeService.getEmployee();
//    }

    @Operation(summary = "This api provide employee by employee id", description = "This is description")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "This api will fetch employee from database whose employee id matches with id in database", content = @Content(mediaType = "application/json"))})
    @GetMapping("/{employee-id}")
    public EmployeeResponseModel getEmployeeById(@PathVariable(value = "employee-id") String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/departments/{department-id}")
    public List<EmployeeResponseModel> getEmployeeByDepartmentId(@PathVariable(value = "department-id") String id) {
        return employeeService.getEmployeeByDepartmentId(id);
    }

    @GetMapping()
    public List<EmployeeResponseModel> getAllEmployeesByQuery(@RequestParam(value = "page-no", required = false, defaultValue = "0") int pageNo, @RequestParam(value = "page-size", required = false, defaultValue = "2") int pageSize) {
        return employeeService.getAllEmployeesByQueryByPageNoAndPageSize(pageNo, pageSize);
    }

//    @GetMapping("/projects/{project-id}")
//    public List<EmployeeResponseModel> getEmployeesByProjectId(@PathVariable(value = "project-id") String id)
//    {
//        return employeeService.getEmployeesByProjectId(id);
//    }

    @GetMapping("/projects/{project-id}")
    public List<EmployeeResponseModel> getEmployeesByProjectIdByPagination(@PathVariable(value = "project-id") String id, @RequestParam(value = "page-no", required = false, defaultValue = "0") int pageNo, @RequestParam(value = "page-size", required = false, defaultValue = "1") int pageSize) {
        return employeeService.getEmployeesByProjectIdByPagination(id, pageNo, pageSize);
    }

    @GetMapping("/query")
    public List<EmployeeResponseModel> getAllEmployeesByQuery() {
        return employeeService.getAllEmployeesByQuery();
    }

    @GetMapping("/query/{employee_id}")
    public EmployeeResponseModel getAllEmployeesByQuery(@PathVariable(value = "employee_id",required = false) @NotNull String id) {
        return employeeService.getAllEmployeesByQueryId(id);
    }

    @GetMapping("/native_query")
    public List<EmployeeResponseModel> getAllEmployeesByNativeQuery() {
        return employeeService.getAllEmployeesByNativeQuery();
    }

    @GetMapping("/native_query/{employee_id}")
    public EmployeeResponseModel getAllEmployeesByNativeQueryById(@PathVariable(value = "employee_id") String id) {
        return employeeService.getAllEmployeesByNativeQueryById(id);
    }

    @GetMapping("/pagination/{employee-id}/{page}")
    public Page<Employee> getAllEmployeesByPagination(@PathVariable(value = "page") int page, @PathVariable(value = "employee-id") String id) {
        return employeeService.getAllEmployeesByPaginationById(page, id);
    }

    @GetMapping("/pagination/{page}")
    public List<EmployeeResponseModel> getAllEmployeesByPagination(@PathVariable(value = "page") int page) {
        return employeeService.getAllEmployeesByPagination(page);
    }

    @Operation(summary = "This Api update data in employee table...", description = "this api accepts request with employee id ,using that id we update data in employee table")
    @ApiResponse(responseCode = "200", description = "Employee data updated in database")
    @PutMapping()
    public EmployeeResponseModel updateEmployee(@RequestBody EmployeeRequestModel employee) {
        return projectEmployeeService.updateEmployee(employee);
    }

    @Operation(summary = "This Api Remove Employee from database", description = "This api accepts employee id and remove that employee from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Employee removed successfully"), @ApiResponse(responseCode = "500", description = "Internal Server Error or Employee not found")})
    @DeleteMapping("/{employee-id}")
    public void deleteEmployee(@PathVariable(value = "employee-id") String id) {
        employeeService.deleteEmployee(id);
    }

}
