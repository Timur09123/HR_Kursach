package com.example.hr;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "leave_requests")
public class LeaveRequest {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int employeeId;
    private int daysRequested;

    public LeaveRequest(int employeeId, int daysRequested) {
        this.employeeId = employeeId;
        this.daysRequested = daysRequested;
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

    public int getDaysRequested() {
        return daysRequested;
    }
}
