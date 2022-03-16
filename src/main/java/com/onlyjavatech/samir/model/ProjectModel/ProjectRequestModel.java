package com.onlyjavatech.samir.model.ProjectModel;
import com.onlyjavatech.samir.model.Employee;
import java.util.List;

public class ProjectRequestModel {
    private String id;
    private String projectName;
    private List<Employee> employeeDetails;

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

    public List<Employee> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<Employee> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
