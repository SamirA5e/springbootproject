package com.onlyjavatech.samir.model;

import com.onlyjavatech.samir.model.ProjectModel.ProjectRequestModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRequestModel {
    private String id;
    private String firstname;
    private String lastname;
    private String emailId;
    private String departmentId;
    private List<ProjectRequestModel> projects = new ArrayList<>();
    private List<ProjectRequestModel> removeProjects = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List<ProjectRequestModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectRequestModel> projects) {
        this.projects = projects;
    }

    public List<ProjectRequestModel> getRemoveProjects() {
        return removeProjects;
    }

    public void setRemoveProjects(List<ProjectRequestModel> removeProjects) {
        this.removeProjects = removeProjects;
    }
}
