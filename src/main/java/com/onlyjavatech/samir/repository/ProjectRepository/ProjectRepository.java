package com.onlyjavatech.samir.repository.ProjectRepository;

import com.onlyjavatech.samir.model.Employee;
import com.onlyjavatech.samir.model.ProjectModel.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {
//    List<Employee> findByEmployees_id(String id);
}
