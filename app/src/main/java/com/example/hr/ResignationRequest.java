package com.example.hr;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "resignation_requests")
public class ResignationRequest {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int employeeId;

    public ResignationRequest(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
