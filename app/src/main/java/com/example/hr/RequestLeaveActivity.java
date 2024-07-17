package com.example.hr;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class RequestLeaveActivity extends AppCompatActivity {
    private EditText editTextEmployeeId, editTextDaysRequested;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_leave);

        editTextEmployeeId = findViewById(R.id.edit_text_employee_id);
        editTextDaysRequested = findViewById(R.id.edit_text_days_requested);
        Button buttonRequestLeave = findViewById(R.id.button_request_leave);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        buttonRequestLeave.setOnClickListener(v -> {
            String idString = editTextEmployeeId.getText().toString().trim();
            String daysRequestedString = editTextDaysRequested.getText().toString().trim();

            if (TextUtils.isEmpty(idString) || TextUtils.isEmpty(daysRequestedString)) {
                Toast.makeText(this, "Please enter employee ID and days requested", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idString);
            int daysRequested = Integer.parseInt(daysRequestedString);
            LeaveRequest leaveRequest = new LeaveRequest(id, daysRequested);
            employeeViewModel.insertLeaveRequest(leaveRequest);
            Toast.makeText(this, "Leave request submitted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
