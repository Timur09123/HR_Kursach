package com.example.hr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeDetailActivity extends AppCompatActivity {
    public static final String EXTRA_EMPLOYEE_ID = "com.example.hr.EXTRA_EMPLOYEE_ID";

    private TextView textViewWelcome;
    private Button buttonProfile, buttonLeaveRequest, buttonResignationRequest,buttonLogout;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonProfile = findViewById(R.id.buttonProfile);
        buttonLeaveRequest = findViewById(R.id.buttonLeaveRequest);
        buttonResignationRequest = findViewById(R.id.buttonResignationRequest);
        buttonLogout = findViewById(R.id.buttonLogout);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        int employeeId = getIntent().getIntExtra(EXTRA_EMPLOYEE_ID, -1);
        if (employeeId != -1) {
            employeeViewModel.getEmployeeById(employeeId).observe(this, new Observer<Employee>() {
                @Override
                public void onChanged(Employee employee) {
                    if (employee != null) {
                        textViewWelcome.setText("Добро пожаловать " + employee.getName());
                    }
                }
            });
        }

        buttonProfile.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeeDetailActivity.this, ProfileActivity.class);
            intent.putExtra(EXTRA_EMPLOYEE_ID, employeeId);
            startActivity(intent);
        });

        buttonLeaveRequest.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeeDetailActivity.this, RequestLeaveActivity.class);
            intent.putExtra(EXTRA_EMPLOYEE_ID, employeeId);
            startActivity(intent);
        });

        buttonResignationRequest.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeeDetailActivity.this, EmployeeActivity.class);
            intent.putExtra(EXTRA_EMPLOYEE_ID, employeeId);
            startActivity(intent);
        });

        buttonLogout.setOnClickListener(v -> {
            // Возврат к экрану входа
            Intent intent = new Intent(EmployeeDetailActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
