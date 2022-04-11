package com.onlyjavatech.samir.service.DepartmentService;

import com.onlyjavatech.samir.exception.ObjectNotFoundException;
import com.onlyjavatech.samir.exception.ValidationHandler;
import com.onlyjavatech.samir.model.DepartmentModel.Department;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentRequestModel;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.repository.DepartmentRepository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentResponseModel registerDepartment(DepartmentRequestModel request) {
        if (request == null) {
            throw new ObjectNotFoundException("Department request can't be empty or null", HttpStatus.BAD_REQUEST);
        }
        if (request.getDepartment_name() == null || request.getDepartment_name().isEmpty()) {
            throw new ValidationHandler("Department name can't be null or empty", HttpStatus.BAD_REQUEST);
        }
        Department department = new Department();
        department.setDepartment_name(request.getDepartment_name());
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        department.setId(uuidAsString);
        Department newDepartment = departmentRepository.save(department);
        return setDepartResponseModel(newDepartment);
    }

    public List<DepartmentResponseModel> getDepartments() {
        Iterable<Department> departments = departmentRepository.findAll();
        List<DepartmentResponseModel> responseList = new ArrayList<>();
//        ---------  for loop for departments object ---------
//        for (Department department : departments) {
//            DepartmentResponseModel department_row = setDepartResponseModel(department);
//            responseList.add(department_row);
//        }
        departments.forEach(department -> {
            DepartmentResponseModel rowDepartment = setDepartResponseModel(department);
            responseList.add(rowDepartment);
        });
        return responseList;
    }

    public DepartmentResponseModel getDepartmentById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ValidationHandler("Department Id is not valid", HttpStatus.BAD_REQUEST);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            throw new ValidationHandler("Department Id is not present in Database", HttpStatus.BAD_REQUEST);
        }
        Department department = optionalDepartment.get();
        return setDepartResponseModel(department);
    }

    public DepartmentResponseModel updateDepartment(DepartmentRequestModel request) {
        if (request == null) {
            throw new ObjectNotFoundException("Department request can't be empty or null", HttpStatus.BAD_REQUEST);
        }
        if (request.getId() == null || request.getId().isEmpty()) {
            throw new ValidationHandler("Department id can't be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (request.getDepartment_name() == null || request.getDepartment_name().isEmpty()) {
            throw new ValidationHandler("Department name can't be null or empty", HttpStatus.BAD_REQUEST);
        }

        String id = request.getId();
        System.out.println(id);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            throw new ValidationHandler("Department Id is not present in database, please provide valid department id", HttpStatus.BAD_REQUEST);
        }
        Department department = optionalDepartment.get();
        department.setId(request.getId());
        department.setDepartment_name(request.getDepartment_name());
        Department updatedDepartment = departmentRepository.save(department);
        return setDepartResponseModel(updatedDepartment);

    }

    public void deleteDepartment(String id) {
        if (id == null || id.isEmpty()) {
            throw new ValidationHandler("Department Id can't be null or empty", HttpStatus.BAD_REQUEST);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            throw new ValidationHandler("Department Id is not present in our database", HttpStatus.BAD_REQUEST);
        }
        departmentRepository.deleteById(id);
    }

    public Department getDepartmentByDepartmentId(String id) {
        if (id == null || id.isEmpty()) {
            throw new ValidationHandler("Department Id can't be null or empty...", HttpStatus.BAD_REQUEST);
        }
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()) {
            throw new ValidationHandler("Department Id is not present in our database....", HttpStatus.BAD_REQUEST);
        }
        return optionalDepartment.get();
    }

    public DepartmentResponseModel setDepartResponseModel(Department department) {
        DepartmentResponseModel response = new DepartmentResponseModel();
        response.setId(department.getId());
        response.setDepartment_name(department.getDepartment_name());
        return response;
    }

}
