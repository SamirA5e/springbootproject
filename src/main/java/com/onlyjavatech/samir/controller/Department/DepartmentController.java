package com.onlyjavatech.samir.controller.Department;

import com.onlyjavatech.samir.model.DepartmentModel.DepartmentRequestModel;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseModel registerDepartment(@RequestBody DepartmentRequestModel department) {
        return departmentService.registerDepartment(department);
    }

    @GetMapping
    public List<DepartmentResponseModel> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/{department-id}")
    public DepartmentResponseModel getDepartmentById(@PathVariable(value = "department-id") String id){
        return departmentService.getDepartmentById(id);
    }

    @PutMapping()
    public DepartmentResponseModel updateDepartment(@RequestBody DepartmentRequestModel department) {
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{department-id}")
    public void deleteDepartment(@PathVariable(value = "department-id") String id) {
        System.out.println("--------====++++++++-----");
        System.out.println(id);
        departmentService.deleteDepartment(id);
    }

}
