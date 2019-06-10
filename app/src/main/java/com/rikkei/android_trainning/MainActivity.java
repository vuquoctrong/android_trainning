package com.rikkei.android_trainning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterStudent adapter;
    private List<Student> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.studentsList);
        students = new ArrayList<>();
        adapter = new AdapterStudent();
        adapter.setStudents(students);
        for(int i = 0 ; i < 10; i ++){
            students.add(new Student("Student Name"+i , 1995 + i));
        }
        students.add(new Student("Student Nameaffffffffff" , 1995220 ) );
        students.add(new Student("Student Nameaffffffffffffffffff" , 1995220 ) );
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}
