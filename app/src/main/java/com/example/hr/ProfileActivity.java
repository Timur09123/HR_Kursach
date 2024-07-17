package com.example.hr;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    public static final String EXTRA_EMPLOYEE_ID = "com.example.hr.EXTRA_EMPLOYEE_ID";

    private TextView textViewName, textViewPosition, textViewSalary, textViewLeave;
    private ImageView imageViewProfile;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewName = findViewById(R.id.textViewPersonalInfo);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        textViewPosition = findViewById(R.id.textViewPosition);
        textViewSalary = findViewById(R.id.textViewSalary);
        textViewLeave = findViewById(R.id.textViewLeave);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        int employeeId = getIntent().getIntExtra(EXTRA_EMPLOYEE_ID, -1);
        Log.d(TAG, "Employee ID: " + employeeId);
        if (employeeId != -1) {
            employeeViewModel.getEmployeeById(employeeId).observe(this, employee -> {
                if (employee != null) {
                    Log.d(TAG, "Employee found: " + employee.getName());
                    textViewName.setText(" " + employee.getName());
                    textViewPosition.setText("Должность: " + employee.getPosition());
                    textViewSalary.setText("Зарплата: " + employee.getSalary() + " рублей");
                    textViewLeave.setText("Отпуск: " + employee.getLeave() + " дней");

                } else {
                    Log.d(TAG, "Employee not found");
                }
            });
        }
    }
}
