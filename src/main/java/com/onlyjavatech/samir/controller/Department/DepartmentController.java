package com.onlyjavatech.samir.controller.Department;

import com.onlyjavatech.samir.model.DepartmentModel.DepartmentRequestModel;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Table;
import java.util.List;

@Tag(name = "Department Controller",description = "This controller performs operations on department table")
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "This Api Stores new department in department table",description = "This Api accepts department request and stores departments")
    @ApiResponse(responseCode = "200",description = "Department stored successfully")
    @PostMapping
    public DepartmentResponseModel registerDepartment(@RequestBody DepartmentRequestModel department) {
        return departmentService.registerDepartment(department);
    }

    @Operation(summary = "This api Fetch all Departments from department table",description = "This api fetch all departments from department table")
    @ApiResponse(responseCode = "200",description = "Department fetch Successfully")
    @GetMapping
    public List<DepartmentResponseModel> getDepartments() {
        return departmentService.getDepartments();
    }

    @Operation(summary = "This Api fetch particular department",description = "This Api fetch particular department by department id")
    @ApiResponse(responseCode = "200",description = "Department Fetch Successfully")
    @GetMapping("/{department-id}")
    public DepartmentResponseModel getDepartmentById(@PathVariable(value = "department-id") String id) {
        return departmentService.getDepartmentById(id);
    }

    @Operation(summary = "This Api update Department data in department table",description = "This Api update department data in department table")
    @ApiResponse(responseCode = "200",description = "Department data updated successfully")
    @PutMapping()
    public DepartmentResponseModel updateDepartment(@RequestBody DepartmentRequestModel department) {
        return departmentService.updateDepartment(department);
    }

    @Operation(summary = "This Api remove department from departments table",description = "This Api remove particular department by taking department id")
    @ApiResponse(responseCode = "200",description = "Department remove successfully")
    @DeleteMapping("/{department-id}")
    public void deleteDepartment(@PathVariable(value = "department-id") String id) {
        departmentService.deleteDepartment(id);
    }

}
