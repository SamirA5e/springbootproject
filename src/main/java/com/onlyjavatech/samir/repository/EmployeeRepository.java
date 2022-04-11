package com.onlyjavatech.samir.repository;

import com.onlyjavatech.samir.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findByProjects_Id(String id);

    @Query("Select employee From Employee employee")
    List<Employee> getAllEmployeesByQuery();

    @Query("Select employee From Employee employee WHERE employee.id=:id")
    Employee getAllEmployeesByQueryId(@Param(value = "id") String id);

    @Query(value = "Select * From employees",nativeQuery = true)
    List<Employee> getAllEmployeesByNativeQuery();

    @Query(value = "select * from employees where id=:emp_id",nativeQuery = true)
    Employee getAllEmployeesByNativeQueryId(@Param(value = "emp_id")String id);

//    @Query(
//            value = "SELECT * FROM Employee ORDER BY id",
//            countQuery = "SELECT count(*) FROM Employee",
//            nativeQuery = true)
//    Page<Employee> findAllUsersWithPagination(Pageable pageable);

    @Query(value = "select * from employees where id=:id",nativeQuery = true)
    Page<Employee> getAllEmployeesByPaginationById(@Param(value = "id") String id,Pageable pageable);

//    @Query(value = "select * from employees",countQuery = "SELECT count(*) FROM employees",nativeQuery = true)
    @Query(value = "select * from employees order by id",nativeQuery = true)
    Page<Employee> getAllEmployeesByPagination(Pageable pageable);
}
