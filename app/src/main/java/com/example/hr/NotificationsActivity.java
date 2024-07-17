package com.example.hr;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    private EmployeeViewModel employeeViewModel;
    private LeaveRequestAdapter leaveRequestAdapter;
    private ResignationRequestAdapter resignationRequestAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        RecyclerView recyclerViewLeaveRequests = findViewById(R.id.recycler_view_leave_requests);
        RecyclerView recyclerViewResignationRequests = findViewById(R.id.recycler_view_resignation_requests);

        recyclerViewLeaveRequests.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewResignationRequests.setLayoutManager(new LinearLayoutManager(this));

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        leaveRequestAdapter = new LeaveRequestAdapter(new ArrayList<>(), new LeaveRequestAdapter.OnLeaveRequestClickListener() {
            @Override
            public void onApproveClick(LeaveRequest leaveRequest) {
                employeeViewModel.updateLeaveDays(leaveRequest.getEmployeeId(), leaveRequest.getDaysRequested());
                employeeViewModel.deleteLeaveRequest(leaveRequest);
                Toast.makeText(NotificationsActivity.this, "Leave approved for Employee ID: " + leaveRequest.getEmployeeId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeclineClick(LeaveRequest leaveRequest) {
                employeeViewModel.deleteLeaveRequest(leaveRequest);
                Toast.makeText(NotificationsActivity.this, "Leave declined for Employee ID: " + leaveRequest.getEmployeeId(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewLeaveRequests.setAdapter(leaveRequestAdapter);

        resignationRequestAdapter = new ResignationRequestAdapter(new ArrayList<>(), new ResignationRequestAdapter.OnResignationRequestClickListener() {
            @Override
            public void onApproveClick(ResignationRequest resignationRequest) {
                employeeViewModel.deleteEmployee(resignationRequest.getEmployeeId());
                employeeViewModel.deleteResignationRequest(resignationRequest);
                Toast.makeText(NotificationsActivity.this, "Employee ID: " + resignationRequest.getEmployeeId() + " has been terminated.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeclineClick(ResignationRequest resignationRequest) {
                employeeViewModel.deleteResignationRequest(resignationRequest);
                Toast.makeText(NotificationsActivity.this, "Resignation declined for Employee ID: " + resignationRequest.getEmployeeId(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewResignationRequests.setAdapter(resignationRequestAdapter);

        employeeViewModel.getAllLeaveRequests().observe(this, leaveRequests -> leaveRequestAdapter.updateLeaveRequests(leaveRequests));

        employeeViewModel.getAllResignationRequests().observe(this, resignationRequests -> resignationRequestAdapter.updateResignationRequests(resignationRequests));
    }
}
