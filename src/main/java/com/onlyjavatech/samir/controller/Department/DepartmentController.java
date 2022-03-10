package com.onlyjavatech.samir.controller.Department;

import com.onlyjavatech.samir.model.DepartmentModel.DepartmentRequestModel;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.service.DepartmentService.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    Logger logger = LoggerFactory.getLogger(DepartmentController.class);
//    private static org.apache.log4j.Logger log = Logger.getLogger(LogClass.class);

    //    ------------------- stream -------------------
    List<Integer> integersList = Arrays.asList(1, 2, 3, 4, 5);
    Integer sum = integersList.stream().reduce(0, Integer::sum);
    Integer maxValue = integersList.stream().reduce(0, Integer::max);
    List<Integer> square = integersList.stream().map(x -> x * x).collect(Collectors.toList());
    int even = integersList.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
    int sumAll = integersList.stream().reduce(5, (ans, i) -> ans + i);
    int multiplication = integersList.stream().reduce(2, (ans, i) -> ans * i);
    float division = integersList.stream().reduce(1, (ans, i) -> ans / i);


    List<String> names = Arrays.asList("samir","","arbaz","tejas","nikhil");





    //    --------------------- end stream -------------
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
        logger.info("-- sum --");
        logger.info(""+sum);
        logger.info("-- sumAll --");
        logger.info(""+sumAll);
        logger.info("-- multiplication --");
        logger.info(""+multiplication);

        logger.info("-- devision --");
        logger.info(""+division);
        logger.info("-- maxValue --");
        logger.info(""+maxValue);
        logger.info("-- square --");
        logger.info(""+square);
        logger.info("-- even --");
        logger.info(""+even);
        logger.info("-- forEach --");
        integersList.stream().map(x -> x * x).forEach(y -> System.out.println(y));
        logger.info("-- forEach Samir --");
        integersList.forEach(System.out::println);

        logger.info("------- Names -----------");
        names.stream().filter(x->!x.isEmpty()).forEach(System.out::println);

        logger.info(""+names.stream().sorted().collect(Collectors.toList()));
        logger.info("------- Names starts with -----------");
        logger.info(""+names.stream().filter(i->i.startsWith("a")).collect(Collectors.toList()));



        logger.debug("----------------- debug message by samir --------------");
        logger.warn("----------------- warn message by samir --------------");
        logger.error("----------------- Error message by samir --------------");
        logger.trace("----------------- Trace message by samir --------------");

        return departmentService.getDepartments();
    }

    @GetMapping("/{department-id}")
    public DepartmentResponseModel getDepartmentById(@PathVariable(value = "department-id") String id) {
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
