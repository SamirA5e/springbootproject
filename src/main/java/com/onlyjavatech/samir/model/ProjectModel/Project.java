package com.onlyjavatech.samir.model.ProjectModel;

import com.onlyjavatech.samir.model.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="projects")
public class Project {
    @Id
    private String id;
    @Column(name="project_name")
    private String projectName;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;

    public void addEmployee(Employee employee){
        employees.add(employee);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
