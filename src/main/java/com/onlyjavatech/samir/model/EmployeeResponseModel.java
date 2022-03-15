package com.onlyjavatech.samir.model;

import com.onlyjavatech.samir.model.DepartmentModel.DepartmentResponseModel;

public class EmployeeResponseModel {
    private String id;
    private String firstname;
    private String lastname;
    private String emailId;
    private DepartmentResponseModel department;

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

    public DepartmentResponseModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponseModel department) {
        this.department = department;
    }
}
