package com.example.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton genData, clearData;
    private StudentDao studentDao;
    private StudentDatabase studentDatabase;
    private MyPageAdapter pageAdapter;
    LiveData<PagedList<Student>> allStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageAdapter = new MyPageAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        genData = findViewById(R.id.gen_data);
        clearData = findViewById(R.id.clear_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(pageAdapter);
        studentDatabase = StudentDatabase.getDatabase(this);
        studentDao = studentDatabase.getStudentDao();
        allStudents = new LivePagedListBuilder<>(studentDao.getAllStudentsLive(), 2).build();
        allStudents.observe(this, students -> {
            pageAdapter.submitList(students);
        });
        genData.setOnClickListener(v -> {
            Student[] students = new Student[1000];
            for (int i=0;i<students.length;i++){
                students[i] = new Student(String.valueOf(i));
            }
            new InsertAsyncTask(studentDao).execute(students);
        });
        clearData.setOnClickListener(v -> {
            new DeleteAsyncTask(studentDao).execute();
        });
    }

    static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudents(students);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao studentDao;

        DeleteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.deleteAllStudents();
            return null;
        }
    }
}