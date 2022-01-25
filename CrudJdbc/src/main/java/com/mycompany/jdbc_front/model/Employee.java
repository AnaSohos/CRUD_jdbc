package com.mycompany.jdbc_front.model;

import java.util.Objects;

public class Employee {

    private int id;
    private String name;
    private int deptId;
    private int postId;

    public static Employee of(String name, int deptId, int  postId) {
        Employee employee = new Employee();
        employee.name = name;
        employee.deptId = deptId;
         employee.postId = postId;
        return employee;
    }

    public static Employee of(int id, String name, int deptId,  int  postId) {
        Employee employee = new Employee();
        employee.id = id;
        employee.name = name;
        employee.deptId = deptId;
           employee.postId = postId;
        return employee;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.deptId;
        hash = 67 * hash + this.postId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.deptId != other.deptId) {
            return false;
        }
        if (this.postId != other.postId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", deptId=" + deptId + ", postId=" + postId + '}';
    }


}
