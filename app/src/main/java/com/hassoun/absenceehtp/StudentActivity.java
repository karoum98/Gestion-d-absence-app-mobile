package com.hassoun.absenceehtp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hassoun.absenceehtp.Adapter.studentAdapter;
import com.hassoun.absenceehtp.Model.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mbase,mbase2;
    ArrayList<Student> students;
    String year,module,element;
    Date date;
    DateFormat dateFormat;
    Button validate;
    TextView datepicker;
    com.hassoun.absenceehtp.Adapter.studentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        students=new ArrayList<>();
        datepicker=findViewById(R.id.datepicker);
        recyclerView=findViewById(R.id.recycler_view);
        year=getIntent().getStringExtra("year");
        module=getIntent().getStringExtra("module");
        element=getIntent().getStringExtra("element");
        date=new Date();
        dateFormat=new SimpleDateFormat("dd-MM-yyyy : HH");

        mbase2= FirebaseDatabase.getInstance().getReference("Absence").child(year).child(element).child(module).child(String.valueOf(dateFormat.format(date)));
        mbase= FirebaseDatabase.getInstance().getReference("Students").child(year).child(element);
        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                students.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student student=dataSnapshot.getValue(Student.class);
                    students.add(student);
                }
                studentAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        studentAdapter=new studentAdapter(students,StudentActivity.this,mbase2);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentActivity.this));
        recyclerView.setAdapter(studentAdapter);

        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        datepicker.setText(String.valueOf(dateFormat.format(new Date())));

        validate=findViewById(R.id.valider);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentActivity.this,MainActivity.class));
            }
        });


    }
}