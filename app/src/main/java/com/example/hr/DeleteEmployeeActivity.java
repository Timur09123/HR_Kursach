package com.example.hr;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class DeleteEmployeeActivity extends AppCompatActivity {
    private EditText editTextEmployeeId;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_employee);

        editTextEmployeeId = findViewById(R.id.editTextEmployeeId);
        Button buttonDelete = findViewById(R.id.buttonDeleteEmployee);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        buttonDelete.setOnClickListener(v -> {
            String idString = editTextEmployeeId.getText().toString().trim();

            if (TextUtils.isEmpty(idString)) {
                Toast.makeText(this, "Please enter employee ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idString);
            employeeViewModel.deleteEmployee(id);
            Toast.makeText(this, "Employee deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
