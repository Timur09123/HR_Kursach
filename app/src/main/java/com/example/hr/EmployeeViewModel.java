package com.example.hr;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// Этот класс представляет ViewModel для управления данными между пользовательским интерфейсом и репозиторием

public class EmployeeViewModel extends AndroidViewModel {

    // Репозиторий для выполнения операций с данными
    private EmployeeRepository repository;

    // LiveData объекты для отслеживания изменений в данных
    private LiveData<List<Employee>> allEmployees;
    private LiveData<List<LeaveRequest>> allLeaveRequests;
    private LiveData<List<ResignationRequest>> allResignationRequests;

    // Конструктор, принимающий приложение в качестве параметра
    public EmployeeViewModel(Application application) {
        super(application);
        repository = new EmployeeRepository(application);

        // Получение LiveData объектов из репозитория
        allEmployees = repository.getAllEmployees();
        allLeaveRequests = repository.getAllLeaveRequests();
        allResignationRequests = repository.getAllResignationRequests();
    }

    // Методы для получения LiveData объектов из ViewModel

    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }

    public LiveData<Employee> getEmployeeById(int employeeId) {
        return repository.getEmployeeById(employeeId);
    }

    public LiveData<List<LeaveRequest>> getAllLeaveRequests() {
        return allLeaveRequests;
    }

    public LiveData<List<ResignationRequest>> getAllResignationRequests() {
        return allResignationRequests;
    }

    // Методы для выполнения операций с данными через репозиторий

    public void insert(Employee employee) {
        repository.insert(employee);
    }

    public void update(Employee employee) {
        repository.update(employee);
    }

    public void delete(Employee employee) {
        repository.delete(employee);
    }

    public void deleteEmployee(int employeeId) {
        repository.deleteById(employeeId);
    }

    public void updateLeaveDays(int employeeId, int daysRequested) {
        repository.updateLeaveDays(employeeId, daysRequested);
    }

    public void deleteLeaveRequest(LeaveRequest leaveRequest) {
        repository.deleteLeaveRequest(leaveRequest);
    }

    public void deleteResignationRequest(ResignationRequest resignationRequest) {
        repository.deleteResignationRequest(resignationRequest);
    }

    public void insertLeaveRequest(LeaveRequest leaveRequest) {
        repository.insertLeaveRequest(leaveRequest);
    }

    public void insertResignationRequest(ResignationRequest resignationRequest) {
        repository.insertResignationRequest(resignationRequest);
    }
}
