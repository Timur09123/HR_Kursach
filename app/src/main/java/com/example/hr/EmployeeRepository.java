package com.example.hr;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

// Этот класс представляет репозиторий, который обеспечивает доступ к данным через DAO и базу данных

public class EmployeeRepository {

    // DAO для доступа к базе данных
    private EmployeeDao employeeDao;

    // LiveData объекты для отслеживания изменений в данных
    private LiveData<List<Employee>> allEmployees;
    private LiveData<List<LeaveRequest>> allLeaveRequests;
    private LiveData<List<ResignationRequest>> allResignationRequests;

    // Конструктор, принимающий приложение в качестве параметра
    public EmployeeRepository(Application application) {
        // Получение экземпляра базы данных из приложения
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);

        // Получение DAO из базы данных
        employeeDao = database.employeeDao();

        // Инициализация LiveData объектов, получая данные из DAO
        allEmployees = employeeDao.getAllEmployees();
        allLeaveRequests = employeeDao.getAllLeaveRequests();
        allResignationRequests = employeeDao.getAllResignationRequests();
    }

    // Методы для выполнения операций с данными через DAO

    public LiveData<Employee> getEmployeeById(int employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }

    public LiveData<List<LeaveRequest>> getAllLeaveRequests() {
        return allLeaveRequests;
    }

    public LiveData<List<ResignationRequest>> getAllResignationRequests() {
        return allResignationRequests;
    }

    public void insert(Employee employee) {
        // Выполнение вставки в отдельном потоке через Executor
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.insert(employee);
        });
    }

    public void update(Employee employee) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.update(employee);
        });
    }

    public void delete(Employee employee) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.delete(employee);
        });
    }

    public void deleteById(int employeeId) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.deleteById(employeeId);
        });
    }

    public void updateLeaveDays(int employeeId, int daysRequested) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.updateLeaveDays(employeeId, daysRequested);
        });
    }

    public void deleteLeaveRequest(LeaveRequest leaveRequest) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.deleteLeaveRequest(leaveRequest.getId());
        });
    }

    public void deleteResignationRequest(ResignationRequest resignationRequest) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.deleteResignationRequest(resignationRequest.getId());
        });
    }

    public void insertResignationRequest(ResignationRequest resignationRequest) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.insertResignationRequest(resignationRequest);
        });
    }

    public void insertLeaveRequest(LeaveRequest leaveRequest) {
        EmployeeDatabase.databaseWriteExecutor.execute(() -> {
            employeeDao.insertLeaveRequest(leaveRequest);
        });
    }
}

