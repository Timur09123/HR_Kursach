package com.example.hr;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

// DAO (Data Access Object) интерфейс для взаимодействия с базой данных
@Dao
public interface EmployeeDao {

    // Методы для операций CRUD (Create, Read, Update, Delete) с таблицей employees

    // Вставить нового сотрудника в базу данных
    @Insert
    void insert(Employee employee);

    // Обновить данные о существующем сотруднике
    @Update
    void update(Employee employee);

    // Удалить сотрудника из базы данных
    @Delete
    void delete(Employee employee);

    // Удалить сотрудника по его ID
    @Query("DELETE FROM employees WHERE id = :employeeId")
    void deleteById(int employeeId);

    // Обновить количество отгулов (leave) для сотрудника
    @Query("UPDATE employees SET leave = leave + :daysRequested WHERE id = :employeeId")
    void updateLeaveDays(int employeeId, int daysRequested);

    // Удалить запрос на отпуск по его ID
    @Query("DELETE FROM leave_requests WHERE id = :leaveRequestId")
    void deleteLeaveRequest(int leaveRequestId);

    // Удалить запрос на увольнение по его ID
    @Query("DELETE FROM resignation_requests WHERE id = :resignationRequestId")
    void deleteResignationRequest(int resignationRequestId);

    // Получить сотрудника по его ID с использованием LiveData для реактивного обновления UI
    @Query("SELECT * FROM employees WHERE id = :employeeId")
    LiveData<Employee> getEmployeeById(int employeeId);

    // Получить список всех сотрудников с использованием LiveData для реактивного обновления UI
    @Query("SELECT * FROM employees")
    LiveData<List<Employee>> getAllEmployees();

    // Получить список всех запросов на отпуск с использованием LiveData для реактивного обновления UI
    @Query("SELECT * FROM leave_requests")
    LiveData<List<LeaveRequest>> getAllLeaveRequests();

    // Получить список всех запросов на увольнение с использованием LiveData для реактивного обновления UI
    @Query("SELECT * FROM resignation_requests")
    LiveData<List<ResignationRequest>> getAllResignationRequests();

    // Вставить новый запрос на увольнение
    @Insert
    void insertResignationRequest(ResignationRequest resignationRequest);

    // Вставить новый запрос на отпуск
    @Insert
    void insertLeaveRequest(LeaveRequest leaveRequest);
}
