package com.onlyjavatech.samir.repository;

import com.onlyjavatech.samir.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,String> {

}
