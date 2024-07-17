package com.example.hr;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employees")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String position;
    private String salary;
    private String leave;

    public Employee(String name, String position, String salary, String leave) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.leave = leave;
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

    public String getPosition() {
        return position;
    }

    public String getSalary() {
        return salary;
    }

    public String getLeave() {
        return leave;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

}
