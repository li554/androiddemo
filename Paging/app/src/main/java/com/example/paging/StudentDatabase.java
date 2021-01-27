package com.example.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    static StudentDatabase INSTANCE;
    static synchronized StudentDatabase getDatabase (Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,StudentDatabase.class,"student_database").build();
        }
        return INSTANCE;
    }
    abstract StudentDao getStudentDao();
}
