package com.example.hr;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class EmployeeActivity extends AppCompatActivity {
    private EditText editTextEmployeeId;
    private EmployeeViewModel employeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        editTextEmployeeId = findViewById(R.id.edit_text_employee_id);
        Button buttonResignationRequest = findViewById(R.id.button_resignation_request);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        buttonResignationRequest.setOnClickListener(v -> {
            String idString = editTextEmployeeId.getText().toString().trim();

            if (TextUtils.isEmpty(idString)) {
                Toast.makeText(this, "Please enter employee ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idString);
            ResignationRequest resignationRequest = new ResignationRequest(id);
            employeeViewModel.insertResignationRequest(resignationRequest);
            Toast.makeText(this, "Resignation request submitted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
