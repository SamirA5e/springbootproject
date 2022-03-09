package com.onlyjavatech.samir.controller.Department;

import com.onlyjavatech.samir.model.DepartmentModel.DepartmentRequestModel;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseModel registerDepartment(@RequestBody DepartmentRequestModel department) {
        logger.warn("----------------- warn message by samir --------------");
        return departmentService.registerDepartment(department);
    }

    @GetMapping
    public List<DepartmentResponseModel> getDepartments() {
        logger.info("----------------- Info message by samir --------------");
        logger.debug("----------------- debug message by samir --------------");
        logger.warn("----------------- warn message by samir --------------");
        logger.error("----------------- Error message by samir --------------");
        logger.trace("----------------- Trace message by samir --------------");

        return departmentService.getDepartments();
    }

    @GetMapping("/{department-id}")
    public DepartmentResponseModel getDepartmentById(@PathVariable(value = "department-id") String id){
        logger.info("----------------- Error message by samir --------------");
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
