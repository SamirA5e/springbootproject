package com.onlyjavatech.samir.model.DepartmentModel;

import javax.persistence.*;

@Entity
@Table(name="departments")
public class Department {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name="department_name")
    private String department_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
