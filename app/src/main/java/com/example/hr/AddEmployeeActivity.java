package com.example.hr;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddEmployeeActivity extends AppCompatActivity {
    private EditText editTextName, editTextPosition, editTextSalary, editTextLeave;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        editTextName = findViewById(R.id.edit_text_name);
        editTextPosition = findViewById(R.id.edit_text_position);
        editTextSalary = findViewById(R.id.edit_text_salary);
        editTextLeave = findViewById(R.id.edit_text_leave);
        Button buttonSave = findViewById(R.id.button_save_employee);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String position = editTextPosition.getText().toString().trim();
            String salary = editTextSalary.getText().toString().trim();
            String leave = editTextLeave.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(position) || TextUtils.isEmpty(salary) || TextUtils.isEmpty(leave)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Employee employee = new Employee(name, position, salary, leave);
            employeeViewModel.insert(employee);
            Toast.makeText(this, "Employee added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
