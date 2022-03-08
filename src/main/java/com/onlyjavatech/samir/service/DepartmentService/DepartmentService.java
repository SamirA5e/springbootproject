package com.onlyjavatech.samir.service.DepartmentService;

import com.onlyjavatech.samir.model.DepartmentModel.Department;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentRequestModel;
import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;
import com.onlyjavatech.samir.repository.DepartmentRepository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentResponseModel registerDepartment(DepartmentRequestModel request) {
        Department department = new Department();
        department.setDepartment_name(request.getDepartment_name());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        System.out.println("Your UUID is: " + uuidAsString);
        department.setId(uuidAsString);

        Department newDepartment = departmentRepository.save(department);

        return setDepartResponseModel(newDepartment);
    }

    public List<DepartmentResponseModel> getDepartments() {
        Iterable<Department> departments = departmentRepository.findAll();
        List<DepartmentResponseModel> responseList = new ArrayList<>();

        for (Department department : departments) {
            DepartmentResponseModel department_row = setDepartResponseModel(department);

            responseList.add(department_row);
        }
        return responseList;
    }

    public DepartmentResponseModel updateDepartment(DepartmentRequestModel request) {
        String id = request.getId();
        System.out.println(id);
        Department department = departmentRepository.findById(id).get();
        department.setId(request.getId());
        department.setDepartment_name(request.getDepartment_name());

        Department updatedDepartment = departmentRepository.save(department);
        return setDepartResponseModel(updatedDepartment);

    }

    public void deleteDepartment(String id) {
        System.out.println("--------------");
        System.out.println(id);
        departmentRepository.deleteById(id);
    }

    private DepartmentResponseModel setDepartResponseModel(Department department) {
        DepartmentResponseModel response = new DepartmentResponseModel();
        response.setId(department.getId());
        response.setDepartment_name(department.getDepartment_name());
        return response;
    }
}
