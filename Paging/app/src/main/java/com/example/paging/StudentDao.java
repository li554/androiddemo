package com.example.paging;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface StudentDao {
    @Insert
    void insertStudents(Student... students);
    @Query("DELETE FROM student")
    void deleteAllStudents();
    @Query("SELECT * FROM student ORDER BY ID")
    DataSource.Factory<Integer,Student> getAllStudentsLive();
}
