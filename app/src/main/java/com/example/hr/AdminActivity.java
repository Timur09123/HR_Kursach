package com.example.hr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AdminActivity extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private Button buttonAddEmployee;
    private Button buttonDeleteEmployee;
    private Button buttonShowAllEmployees;
    private Button buttonNotifications;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonDeleteEmployee = findViewById(R.id.buttonDeleteEmployee);
        buttonShowAllEmployees = findViewById(R.id.buttonShowAllEmployees);
        buttonNotifications = findViewById(R.id.buttonNotifications);
        buttonLogout = findViewById(R.id.buttonLogout);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        buttonAddEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddEmployeeActivity.class);
            startActivity(intent);
        });

        buttonDeleteEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, DeleteEmployeeActivity.class);
            startActivity(intent);
        });

        buttonShowAllEmployees.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ShowAllEmployeesActivity.class);
            startActivity(intent);
        });

        buttonNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, NotificationsActivity.class);
            startActivity(intent);
        });

        buttonLogout.setOnClickListener(v -> {
            // Возврат к экрану входа
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
