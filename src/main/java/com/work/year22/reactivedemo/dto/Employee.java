package com.work.year22.reactivedemo.dto;

import java.util.List;

public class Employee {
    
    private int id;
    private String eName;
    private List<String> eAddress;

    public Employee(int id, String eName, List<String> eAddress) {
        this.id = id;
        this.eName = eName;
        this.eAddress = eAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public List<String> geteAddress() {
        return eAddress;
    }

    public void seteAddress(List<String> eAddress) {
        this.eAddress = eAddress;
    }
}
