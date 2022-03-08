package com.onlyjavatech.samir.repository.DepartmentRepository;

import com.onlyjavatech.samir.model.DepartmentModel.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,String> {
}
