package com.example.hr;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Аннотация @Database определяет, что это база данных Room
@Database(entities = {Employee.class, LeaveRequest.class, ResignationRequest.class}, version = 2, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    // Абстрактный метод для получения Dao (Data Access Object)
    public abstract EmployeeDao employeeDao();

    // Переменная INSTANCE, используемая для хранения единственного экземпляра базы данных
    private static volatile EmployeeDatabase INSTANCE;

    // Количество потоков для выполнения операций с базой данных
    private static final int NUMBER_OF_THREADS = 4;

    // Пул потоков для выполнения операций с базой данных
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Метод getInstance для получения единственного экземпляра базы данных
    static EmployeeDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeDatabase.class) {
                if (INSTANCE == null) {
                    // Создание базы данных Room
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EmployeeDatabase.class, "employee_database")
                            .fallbackToDestructiveMigration() // При обновлении базы данных, старая база данных будет удалена
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Callback для базы данных, который может быть использован для выполнения действий при открытии базы данных
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);

                }
            };
}
