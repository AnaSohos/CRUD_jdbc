package com.mycompany.jdbc_front.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

    private int id;
    private String name;
     private String email;
      private String phone; 

    public static Department of(String name, String email, String phone) {
        Department department = new Department();
        department.name = name;
         department.email = email;
          department.phone = phone;
        return department;
    }

    public static Department of(int id, String name) {
        Department department = new Department();
        department.id = id;
        department.name = name;
        return department;
    }
public static Department of(int id, String name, String email, String phone) {
         Department department = new Department();
          department.id = id;
        department.name = name;
         department.email = email;
          department.phone = phone;
        return department;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.phone);
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
        final Department other = (Department) obj;
        return true;
    }

 

    

}
